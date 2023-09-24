package com.sina.service;

import com.sina.domain.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Sina Askarnejad
 */
public interface FileReader {

    FileInfo readContent(MultipartFile file) throws IOException;
}
