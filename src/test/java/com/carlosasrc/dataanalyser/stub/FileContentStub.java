package com.carlosasrc.dataanalyser.stub;

import com.carlosasrc.dataanalyser.model.data.RowData;

import java.util.Arrays;
import java.util.List;

public class FileContentStub {

    public static List<RowData> getRowData() {
        return Arrays.asList(SalesmanStub.build(), CustomerStub.build(), SaleStub.build());
    }

    public static List<String> getContent() {
        return Arrays.asList(
                "001ç1234567891234çPedroç50000",
                "002ç2345675434544345çJose da SilvaçRural",
                "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro"
        );
    }
}
