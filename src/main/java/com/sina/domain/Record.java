package com.sina.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author Sina Askarnejad
 */
@Data
@Builder
public class Record {
    private String source;
    private String codeListCode;
    private String code;
    private String displayValue;
    private String longDescription;
    private LocalDate fromDate;
    private LocalDate toDate;
    private Integer sortingPriority;
}