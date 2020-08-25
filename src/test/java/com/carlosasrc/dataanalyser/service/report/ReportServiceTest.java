package com.carlosasrc.dataanalyser.service.report;

import com.carlosasrc.dataanalyser.model.data.RowData;
import com.carlosasrc.dataanalyser.model.file.DataReport;
import com.carlosasrc.dataanalyser.service.report.CustomerReportService;
import com.carlosasrc.dataanalyser.service.report.ReportService;
import com.carlosasrc.dataanalyser.service.report.SaleReportService;
import com.carlosasrc.dataanalyser.service.report.SalesmanReportService;
import com.carlosasrc.dataanalyser.stub.DataReportStub;
import com.carlosasrc.dataanalyser.stub.FileContentStub;
import com.carlosasrc.dataanalyser.stub.SaleStub;
import com.carlosasrc.dataanalyser.stub.SalesmanStub;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ReportServiceTest {

    @InjectMocks
    private ReportService reportService;

    @Mock
    private SalesmanReportService salesmanReportService;
    @Mock
    private CustomerReportService customerReportService;
    @Mock
    private SaleReportService saleReportService;

    @Test
    public void shouldGenerateReport() {
        when(saleReportService.getMostExpensiveSale(anyList())).thenReturn(SaleStub.build().get(0));
        when(customerReportService.getAmountCustomers(anyList())).thenReturn(2L);
        when(salesmanReportService.getAmountSalesmen(anyList())).thenReturn(2L);
        when(salesmanReportService.getWorstSeller(anyList())).thenReturn(SalesmanStub.build().get(1));

        DataReport expected = DataReportStub.build();
        List<RowData> rowData = FileContentStub.getRowData();

        DataReport generated = reportService.generateReport(rowData);

        Assert.assertEquals(expected, generated);
    }
}
