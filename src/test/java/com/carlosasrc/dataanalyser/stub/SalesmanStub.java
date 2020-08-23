package com.carlosasrc.dataanalyser.stub;

import com.carlosasrc.dataanalyser.model.data.Salesman;

import java.math.BigDecimal;

public class SalesmanStub {
    public static Salesman build() {
        return new Salesman("1234567891234", "Pedro", BigDecimal.valueOf(50000));
    }
}
