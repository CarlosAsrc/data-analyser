package com.carlosasrc.dataanalyser.parser;

import com.carlosasrc.dataanalyser.model.RowData;
import com.carlosasrc.dataanalyser.properties.ParsingProperties;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public abstract class RowDataParser {
    protected final ParsingProperties parsingProperties;

    public abstract RowData parseLine(String line);
}
