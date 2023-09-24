package com.sina.controller;

import com.sina.dto.FileDetailResponseDto;
import com.sina.dto.FileSaveResponseDto;
import com.sina.exception.DataNotFoundException;
import com.sina.exception.InternalServerException;
import com.sina.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Sina Askarnejad
 */
@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PagedModel<FileDetailResponseDto> getAllDetails(@RequestParam(required = false, defaultValue = "0") int page,
                                                           @RequestParam(required = false, defaultValue = "${fetch.page-size}") int limit) throws InternalServerException {
        return fileService.fetchAllDetails(page, limit);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FileSaveResponseDto save(@RequestParam("file") MultipartFile file) throws InternalServerException {
        return fileService.save(file);
    }

    @GetMapping("/record/{code}")
    @ResponseStatus(HttpStatus.OK)
    public FileDetailResponseDto fetchByCode(@PathVariable String code) throws DataNotFoundException, InternalServerException {
        return fileService.findByCode(code);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteAll() throws InternalServerException {
        fileService.deleteAll();
    }
}
