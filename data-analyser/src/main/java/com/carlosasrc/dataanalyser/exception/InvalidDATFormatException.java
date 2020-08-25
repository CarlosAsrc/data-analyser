package com.carlosasrc.dataanalyser.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InvalidDATFormatException extends RuntimeException {
    private String message;
}
