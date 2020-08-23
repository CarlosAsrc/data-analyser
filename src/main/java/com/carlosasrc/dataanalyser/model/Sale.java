package com.carlosasrc.dataanalyser.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class Sale extends RowData {
    private Long id;
    private List<Item> items;
    private String salesmanName;

    public BigDecimal getSalePrice() {
        return items.stream()
                .map(Item::getPrice)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }
}
