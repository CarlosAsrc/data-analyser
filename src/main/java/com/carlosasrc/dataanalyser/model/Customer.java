package com.carlosasrc.dataanalyser.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Customer extends RowData {
    private String cnpj;
    private String name;
    private String businessArea;
}
