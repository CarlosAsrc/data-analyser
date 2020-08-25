package com.carlosasrc.dataanalyser.parser;

import com.carlosasrc.dataanalyser.model.data.RowData;
import com.carlosasrc.dataanalyser.properties.ParsingProperties;
import com.carlosasrc.dataanalyser.stub.CustomerStub;
import com.carlosasrc.dataanalyser.stub.FileContentStub;
import com.carlosasrc.dataanalyser.stub.SaleStub;
import com.carlosasrc.dataanalyser.stub.SalesmanStub;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FileParserTest {

    @InjectMocks
    private FileParser fileParser;
    @Mock
    private ParsingProperties parsingProperties;
    @Mock
    private SalesmanParser salesmanParser;
    @Mock
    private CustomerParser customerParser;
    @Mock
    private SaleParser saleParser;

    @Before
    public void setUp() {
        when(parsingProperties.getSalesmanFormatId()).thenReturn("001");
        when(parsingProperties.getCustomerFormatId()).thenReturn("002");
        when(parsingProperties.getSaleFormatId()).thenReturn("003");
        fileParser = new FileParser(parsingProperties, salesmanParser, customerParser, saleParser);
    }

    @Test
    public void shouldParseRowData() {
        when(customerParser.parseLine(anyString())).thenReturn(CustomerStub.build().get(0));
        when(salesmanParser.parseLine(anyString())).thenReturn(SalesmanStub.build().get(0));
        when(saleParser.parseLine(anyString())).thenReturn(SaleStub.build().get(0));

        List<RowData> expected = FileContentStub.getRowData().subList(0, 3);
        List<RowData> rowData = fileParser.parse(FileContentStub.getContent());

        Assert.assertEquals(expected, rowData);
    }
    
}
