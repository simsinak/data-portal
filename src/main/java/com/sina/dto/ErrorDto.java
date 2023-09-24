package com.sina.dto;

import lombok.Data;

/**
 * @author Sina Askarnejad
 */
@Data
public class ErrorDto {
    private String reason;
    private int code;
}
