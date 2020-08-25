package com.carlosasrc.dataanalyser.service.report;

import com.carlosasrc.dataanalyser.model.data.RowData;
import com.carlosasrc.dataanalyser.model.data.Sale;
import com.carlosasrc.dataanalyser.stub.FileContentStub;
import com.carlosasrc.dataanalyser.stub.SaleStub;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class SaleReportServiceTest {

    @InjectMocks
    private SaleReportService saleReportService;

    @Test
    public void shouldReturnMostExpensiveSale() {
        List<RowData> rowData = FileContentStub.getRowData();
        Sale expected = SaleStub.build().get(0);
        Sale mostExpensiveSale = saleReportService.getMostExpensiveSale(rowData);

        Assert.assertEquals(expected, mostExpensiveSale);
    }
}
