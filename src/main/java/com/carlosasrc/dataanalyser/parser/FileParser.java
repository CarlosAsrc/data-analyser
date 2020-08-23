package com.carlosasrc.dataanalyser.parser;

import com.carlosasrc.dataanalyser.model.RowData;
import com.carlosasrc.dataanalyser.properties.ParsingProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class FileParser {

    private final Map<String, RowDataParser> layoutParser;

    public FileParser (ParsingProperties parsingProperties,
                       SalesmanParser salesmanParser,
                       CustomerParser customerParser,
                       SaleParser saleParser) {
        layoutParser = new HashMap<String, RowDataParser>();
        layoutParser.put(parsingProperties.getSalesmanFormatId(), salesmanParser);
        layoutParser.put(parsingProperties.getCustomerFormatId(), customerParser);
        layoutParser.put(parsingProperties.getSaleFormatId(), saleParser);
    }

    public List<RowData> parse(List<String> listLines) {
        return listLines.stream()
                .map(this::parseRowData)
                .collect(Collectors.toList());
    }

    private RowData parseRowData(String s) {
        return layoutParser.get(s.substring(0, 3)).parseLine(s);
    }
}