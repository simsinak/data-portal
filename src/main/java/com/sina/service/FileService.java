package com.sina.service;

import com.sina.dto.FileDetailResponseDto;
import com.sina.dto.FileSaveResponseDto;
import com.sina.exception.DataNotFoundException;
import com.sina.exception.InternalServerException;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Sina Askarnejad
 */
public interface FileService {

    FileSaveResponseDto save(MultipartFile file) throws InternalServerException;

    PagedModel<FileDetailResponseDto> fetchAllDetails(int page, int limit) throws InternalServerException;

    FileDetailResponseDto findByCode(String code) throws DataNotFoundException, InternalServerException;

    void deleteAll() throws InternalServerException;
}
