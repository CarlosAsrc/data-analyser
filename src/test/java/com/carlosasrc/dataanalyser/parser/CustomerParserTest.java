package com.carlosasrc.dataanalyser.parser;

import com.carlosasrc.dataanalyser.model.data.Customer;
import com.carlosasrc.dataanalyser.properties.ParsingProperties;
import com.carlosasrc.dataanalyser.stub.CustomerStub;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.management.BufferPoolMXBean;
import java.math.BigDecimal;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerParserTest {

    @InjectMocks
    private CustomerParser customerParser;
    @Mock
    private ParsingProperties parsingProperties;

    @Before
    public void setUp() {
        when(parsingProperties.getRowDataSplitter()).thenReturn("ç");
        when(parsingProperties.getCustomerCnpjIndex()).thenReturn(1);
        when(parsingProperties.getCustomerNameIndex()).thenReturn(2);
        when(parsingProperties.getCustomerBusinessAreaIndex()).thenReturn(3);
        when(parsingProperties.getCustomerRegex()).thenReturn("^([0-9]{3})ç([0-9]{12,16})ç([a-zA-Z\\s]+)ç(.*)$");
    }

    @Test
    public void shouldBuildSalesmanDataRow() {
        Customer expected = CustomerStub.build().get(0);
        Customer customer = (Customer) customerParser.parseLine("002ç2345675434544345çJose da SilvaçRural");

        Assert.assertEquals(expected, customer);
    }

    @Test
    public void shouldReturnValid() {
        Boolean valid = customerParser.validateLine("002ç2345675434544345çJose da SilvaçRural");
        Assert.assertTrue(valid);
    }

    @Test
    public void shouldReturnInvalid() {
        Boolean valid = customerParser.validateLine("002ç234567543sd4544345çJose da SilvaçRural");
        Assert.assertFalse(valid);
    }
    
}
