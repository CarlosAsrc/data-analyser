package com.carlosasrc.dataanalyser.stub;

import com.carlosasrc.dataanalyser.model.data.Customer;
import com.carlosasrc.dataanalyser.model.data.Salesman;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class CustomerStub {
    public static List<Customer> build() {
        return Arrays.asList(new Customer("2345675434544345", "Jose da Silva", "Rural"),
                             new Customer("2345675433444345", "Eduardo Pereira", "Rural"));
    }
}
