package com.carlosasrc.dataanalyser.parser;

import com.carlosasrc.dataanalyser.model.data.Item;
import com.carlosasrc.dataanalyser.model.data.RowData;
import com.carlosasrc.dataanalyser.model.data.Sale;
import com.carlosasrc.dataanalyser.properties.ParsingProperties;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SaleParser extends RowDataParser {

    public SaleParser(ParsingProperties parsingProperties) {
        super(parsingProperties);
    }

    @Override
    public RowData parseLine(String line) {
        String [] lineData = line.split(parsingProperties.getRowDataSplitter());
        Long id = new Long(lineData[parsingProperties.getSaleIdIndex()]);
        String salesmanName = lineData[parsingProperties.getSaleNameSalesmanIndex()];
        List<Item> items = buildItems(lineData[parsingProperties.getSaleItemsIndex()]);
        return new Sale(id, items, salesmanName);
    }

    private List<Item> buildItems(String lineItems) {

        List<String> itemsData = Arrays
                .stream(lineItems
                        .replaceAll(parsingProperties.getBracketsRegex(), "")
                        .split(parsingProperties.getItemListSplitter()))
                .collect(Collectors.toList());

        return itemsData.stream()
                .map(this::buildItem)
                .collect(Collectors.toList());
    }

    private Item buildItem(String line) {
        String [] lineData = line.split(parsingProperties.getItemSplitter());
        Long id = new Long(lineData[parsingProperties.getItemIdIndex()]);
        Long quantity = new Long(lineData[parsingProperties.getItemQuantityIndex()]);
        BigDecimal price = new BigDecimal(lineData[parsingProperties.getItemPriceIndex()]);
        return new Item(id, quantity, price);
    }

}
