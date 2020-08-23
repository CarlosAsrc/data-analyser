package com.carlosasrc.dataanalyser.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Item extends RowData {
    private Long id;
    private Long quantity;
    private BigDecimal price;
}
