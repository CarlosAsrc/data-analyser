package com.carlosasrc.dataanalyser.stub;

import com.carlosasrc.dataanalyser.model.data.Item;
import com.carlosasrc.dataanalyser.model.data.Sale;
import com.carlosasrc.dataanalyser.model.data.Salesman;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SaleStub {
    public static Sale build() {
        return new Sale(10L, buildItems(), "Pedro");
    }

    private static List<Item> buildItems() {
        return Arrays.asList(
                buildItem(1L, 10L, new BigDecimal("100")),
                buildItem(2L, 30L, new BigDecimal("2.50")),
                buildItem(3L, 40L, new BigDecimal("3.10"))
        );
    }

    private static Item buildItem(Long id, Long quantity, BigDecimal price) {
        return Item.builder()
                .id(id)
                .quantity(quantity)
                .price(price)
                .build();
    }
}
