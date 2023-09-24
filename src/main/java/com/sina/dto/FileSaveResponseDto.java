package com.sina.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sina Askarnejad
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileSaveResponseDto {
    String fileName;
    int totalRecord;
}
