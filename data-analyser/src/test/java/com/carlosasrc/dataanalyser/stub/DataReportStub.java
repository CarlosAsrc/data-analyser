package com.carlosasrc.dataanalyser.stub;

import com.carlosasrc.dataanalyser.model.file.DataReport;

public class DataReportStub {
    public static DataReport build() {
        return new DataReport(2L, 2L, SaleStub.build().get(0), SalesmanStub.build().get(1));
    }
}
