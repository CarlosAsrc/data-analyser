package com.carlosasrc.dataanalyser.model.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Salesman extends RowData {
    private String cpf;
    private String name;
    private BigDecimal salary;
}
