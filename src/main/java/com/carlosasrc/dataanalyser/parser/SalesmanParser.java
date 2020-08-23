package com.carlosasrc.dataanalyser.parser;

import com.carlosasrc.dataanalyser.model.data.RowData;
import com.carlosasrc.dataanalyser.model.data.Salesman;
import com.carlosasrc.dataanalyser.properties.ParsingProperties;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SalesmanParser extends RowDataParser {

    public SalesmanParser(ParsingProperties parsingProperties) {
        super(parsingProperties);
    }

    @Override
    public RowData parseLine(String line) {
        String [] lineData = line.split(parsingProperties.getRowDataSplitter());
        String cpf = lineData[parsingProperties.getSalesmanCpfIndex()];
        String name = lineData[parsingProperties.getSalesmanNameIndex()];
        BigDecimal salary = new BigDecimal(lineData[parsingProperties.getSalesmanSalaryIndex()]);
        return new Salesman(cpf, name, salary);
    }
}
