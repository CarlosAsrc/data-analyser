package com.carlosasrc.dataanalyser.parser;

import com.carlosasrc.dataanalyser.exception.InvalidDATFormatException;
import com.carlosasrc.dataanalyser.model.data.RowData;
import com.carlosasrc.dataanalyser.properties.ParsingProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Getter
@Setter
public class FileParser {

    private final Map<String, RowDataParser> layoutParser;

    public FileParser (ParsingProperties parsingProperties,
                       SalesmanParser salesmanParser,
                       CustomerParser customerParser,
                       SaleParser saleParser) {
        layoutParser = new HashMap<>();
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
        RowDataParser parser = layoutParser.get(s.substring(0, 3));
        if(!parser.validateLine(s)) {
            throw new InvalidDATFormatException(String.format("Invalid format line: %s", s));
        }
        return  parser.parseLine(s);
    }
}
