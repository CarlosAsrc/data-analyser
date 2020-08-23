package com.carlosasrc.dataanalyser.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Salesman extends RowData {
    private String cpf;
    private String name;
    private BigDecimal salary;
}
