package com.carlosasrc.dataanalyser.model.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Item extends RowData {
    private Long id;
    private Long quantity;
    private BigDecimal price;
}
