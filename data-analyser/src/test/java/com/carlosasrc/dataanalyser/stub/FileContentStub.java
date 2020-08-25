package com.carlosasrc.dataanalyser.stub;

import com.carlosasrc.dataanalyser.model.data.RowData;

import java.util.Arrays;
import java.util.List;

public class FileContentStub {

    public static List<RowData> getRowData() {
        return Arrays.asList(SalesmanStub.build().get(0), CustomerStub.build().get(0), SaleStub.build().get(0),
                             SalesmanStub.build().get(1), CustomerStub.build().get(1), SaleStub.build().get(1));
    }

    public static List<String> getContent() {
        return Arrays.asList(
                "001ç1234567891234çPedroç50000",
                "002ç2345675434544345çJose da SilvaçRural",
                "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro"
        );
    }

    public static List<String> getFullFileContent() {
        return Arrays.asList(
            "001ç1234567891234çPedroç50000",
            "002ç2345675434544345çJose da SilvaçRural",
            "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro",
            "001ç3245678865434çPauloç40000.99",
            "002ç2345675433444345çEduardo PereiraçRural",
            "003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo"
        );
    }
}
