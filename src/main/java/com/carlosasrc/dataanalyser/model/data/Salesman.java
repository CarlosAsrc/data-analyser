package com.carlosasrc.dataanalyser.model.data;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Salesman extends RowData {
    private String cpf;
    private String name;
    private BigDecimal salary;
}
