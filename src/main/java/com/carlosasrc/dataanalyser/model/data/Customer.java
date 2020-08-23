package com.carlosasrc.dataanalyser.model.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Customer extends RowData {
    private String cnpj;
    private String name;
    private String businessArea;
}
