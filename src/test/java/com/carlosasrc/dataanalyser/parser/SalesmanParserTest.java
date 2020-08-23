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
    }

    @Test
    public void shouldBuildSalesmanDataRow() {
        Salesman expected = SalesmanStub.build();
        Salesman salesman = (Salesman) salesmanParser.parseLine("001ç1234567891234çPedroç50000");

        Assert.assertEquals(expected, salesman);
    }

}
