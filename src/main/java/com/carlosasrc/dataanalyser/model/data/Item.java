package com.carlosasrc.dataanalyser.model.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Item extends RowData {
    private Long id;
    private Long quantity;
    private BigDecimal price;
}
