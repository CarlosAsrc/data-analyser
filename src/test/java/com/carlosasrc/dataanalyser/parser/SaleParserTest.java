package com.carlosasrc.dataanalyser.parser;

import com.carlosasrc.dataanalyser.model.data.Sale;
import com.carlosasrc.dataanalyser.properties.ParsingProperties;
import com.carlosasrc.dataanalyser.stub.SaleStub;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SaleParserTest {

    @InjectMocks
    private SaleParser saleParser;
    @Mock
    private ParsingProperties parsingProperties;

    @Before
    public void setUp() {
        when(parsingProperties.getRowDataSplitter()).thenReturn("ç");
        when(parsingProperties.getSaleIdIndex()).thenReturn(1);
        when(parsingProperties.getSaleNameSalesmanIndex()).thenReturn(3);
        when(parsingProperties.getSaleItemsIndex()).thenReturn(2);
        when(parsingProperties.getBracketsRegex()).thenReturn("[\\[\\]]");
        when(parsingProperties.getItemIdIndex()).thenReturn(0);
        when(parsingProperties.getItemQuantityIndex()).thenReturn(1);
        when(parsingProperties.getItemPriceIndex()).thenReturn(2);
        when(parsingProperties.getItemListSplitter()).thenReturn(",");
        when(parsingProperties.getItemSplitter()).thenReturn("-");
    }

    @Test
    public void shouldBuildSalesmanDataRow() {
        Sale expected = SaleStub.build();
        Sale Sale = (Sale) saleParser.parseLine("003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro");

        Assert.assertEquals(expected, Sale);
    }

}
