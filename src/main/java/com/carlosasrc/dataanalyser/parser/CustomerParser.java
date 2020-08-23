package com.carlosasrc.dataanalyser.parser;

import com.carlosasrc.dataanalyser.model.Customer;
import com.carlosasrc.dataanalyser.model.RowData;
import com.carlosasrc.dataanalyser.properties.ParsingProperties;
import org.springframework.stereotype.Component;

@Component
public class CustomerParser extends RowDataParser {

    public CustomerParser(ParsingProperties parsingProperties) {
        super(parsingProperties);
    }

    @Override
    public RowData parseLine(String line) {
        String [] lineData = line.split(parsingProperties.getRowDataSplitter());
        String cpnj = lineData[parsingProperties.getCustomerCnpjIndex()];
        String name = lineData[parsingProperties.getCustomerNameIndex()];
        String businessArea = lineData[parsingProperties.getCustomerBusinessAreaIndex()];
        return new Customer(cpnj, name, businessArea);
    }
}
