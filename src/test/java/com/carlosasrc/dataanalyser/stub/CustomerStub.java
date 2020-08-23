package com.carlosasrc.dataanalyser.stub;

import com.carlosasrc.dataanalyser.model.data.Customer;
import com.carlosasrc.dataanalyser.model.data.Salesman;

import java.math.BigDecimal;

public class CustomerStub {
    public static Customer build() {
        return new Customer("2345675434544345", "Jose da Silva", "Rural");
    }
}
