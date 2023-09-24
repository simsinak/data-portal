package com.sina.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

/**
 * @author Sina Askarnejad
 */
@Data
@Builder
public class FileDetailResponseDto extends RepresentationModel<FileDetailResponseDto> {
    private long id;
    private String source;
    private String codeListCode;
    private String code;
    private String displayValue;
    private String longDescription;
    private LocalDate fromDate;
    private LocalDate toDate;
    private Integer sortingPriority;
}
