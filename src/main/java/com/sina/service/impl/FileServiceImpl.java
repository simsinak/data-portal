package com.sina.service.impl;

import com.sina.assembler.FileDetailAssembler;
import com.sina.domain.FileInfo;
import com.sina.dto.FileDetailResponseDto;
import com.sina.dto.FileSaveResponseDto;
import com.sina.entity.FileDetail;
import com.sina.exception.DataNotFoundException;
import com.sina.exception.InternalServerException;
import com.sina.repository.FileDetailDao;
import com.sina.service.FileReader;
import com.sina.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * @author Sina Askarnejad
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class FileServiceImpl implements FileService {

    private final FileDetailDao fileDetailDao;
    private final FileReader fileReader;
    private final FileDetailAssembler assembler;
    private final PagedResourcesAssembler<FileDetail> pagedResourcesAssembler;

    @Override
    public FileSaveResponseDto save(MultipartFile file) throws InternalServerException {
        FileInfo fileInfo = null;
        try {
            fileInfo = fileReader.readContent(file);
        } catch (IOException e) {
            throw new InternalServerException("Failed to read file contents", e);
        }
        try {
            List<FileDetail> fileDetails = assembler.convertRecordsToEntity(fileInfo.records());
            fileDetailDao.saveAll(fileDetails);
            log.info("File content saved successfully, count: {}", fileDetails.size());
            return new FileSaveResponseDto(file.getOriginalFilename(), fileDetails.size());
        } catch (Exception e) {
            log.error("Failed to save file content", e);
            throw new InternalServerException("Database Error", e);
        }
    }

    @Override
    public PagedModel<FileDetailResponseDto> fetchAllDetails(int page, int limit) throws InternalServerException {
        try {
            Page<FileDetail> fetchResult = fileDetailDao.findAll(PageRequest.of(page, limit).withSort(Sort.by(Sort.Direction.ASC, "id")));
            return pagedResourcesAssembler.toModel(fetchResult, assembler);
        } catch (Exception e) {
            log.error("Failed to fetch contents", e);
            throw new InternalServerException("Database Error", e);
        }
    }

    @Override
    public FileDetailResponseDto findByCode(String code) throws DataNotFoundException, InternalServerException {
        try {
            Optional<FileDetail> optionalResult = fileDetailDao.findFileDetailByCode(code);
            if (optionalResult.isPresent()) {
                return assembler.toModel(optionalResult.get());
            } else {
                throw new DataNotFoundException("Data with code" + code + "does not exists");
            }
        } catch (DataNotFoundException e) {
            log.info("Data with code" + code + "does not exists");
            throw e;
        } catch (Exception e) {
            log.error("Failed to fetch contents", e);
            throw new InternalServerException("Database Error", e);
        }
    }

    @Override
    public void deleteAll() throws InternalServerException {
        try {
            fileDetailDao.deleteAll();
        } catch (Exception e) {
            log.error("Failed to fetch contents", e);
            throw new InternalServerException("Database Error", e);
        }
    }
}
