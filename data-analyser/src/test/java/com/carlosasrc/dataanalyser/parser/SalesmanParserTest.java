package com.carlosasrc.dataanalyser.parser;

import com.carlosasrc.dataanalyser.model.data.Salesman;
import com.carlosasrc.dataanalyser.properties.ParsingProperties;
import com.carlosasrc.dataanalyser.stub.SalesmanStub;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SalesmanParserTest {

    @InjectMocks
    private SalesmanParser salesmanParser;
    @Mock
    private ParsingProperties parsingProperties;

    @Before
    public void setUp() {
        when(parsingProperties.getRowDataSplitter()).thenReturn("ç");
        when(parsingProperties.getSalesmanCpfIndex()).thenReturn(1);
        when(parsingProperties.getSalesmanNameIndex()).thenReturn(2);
        when(parsingProperties.getSalesmanSalaryIndex()).thenReturn(3);
        when(parsingProperties.getSalesmanRegex()).thenReturn("^([0-9]{3})ç([0-9]{10,15})ç([a-zA-Zç ]+)ç([0-9.,]+)$");
    }

    @Test
    public void shouldBuildSalesmanDataRow() {
        Salesman expected = SalesmanStub.build().get(0);
        Salesman salesman = (Salesman) salesmanParser.parseLine("001ç1234567891234çPedroç50000");

        Assert.assertEquals(expected, salesman);
    }

    @Test
    public void shouldReturnValid() {
        Boolean valid = salesmanParser.validateLine("001ç1234567891234çPedroç50000");
        Assert.assertTrue(valid);
    }

    @Test
    public void shouldReturnInvalid() {
        Boolean valid = salesmanParser.validateLine("001ç1234567891234çPedroç5000as0");
        Assert.assertFalse(valid);
    }

}
