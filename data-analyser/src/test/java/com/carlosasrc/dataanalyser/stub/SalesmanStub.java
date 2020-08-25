package com.carlosasrc.dataanalyser.stub;

import com.carlosasrc.dataanalyser.model.data.Salesman;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class SalesmanStub {
    public static List<Salesman> build() {
        return Arrays.asList(new Salesman("1234567891234", "Pedro", BigDecimal.valueOf(50000)),
                             new Salesman("3245678865434", "Paulo", BigDecimal.valueOf(40000.99)));
    }
}
