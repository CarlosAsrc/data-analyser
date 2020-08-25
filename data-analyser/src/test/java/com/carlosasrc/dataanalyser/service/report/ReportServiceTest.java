package com.carlosasrc.dataanalyser.service.report;

import com.carlosasrc.dataanalyser.model.data.RowData;
import com.carlosasrc.dataanalyser.model.file.DataReport;
import com.carlosasrc.dataanalyser.service.report.generator.CustomerReportGenerator;
import com.carlosasrc.dataanalyser.service.report.generator.SaleReportGenerator;
import com.carlosasrc.dataanalyser.service.report.generator.SalesmanReportGenerator;
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
    private SalesmanReportGenerator salesmanReportGenerator;
    @Mock
    private CustomerReportGenerator customerReportGenerator;
    @Mock
    private SaleReportGenerator saleReportGenerator;

    @Test
    public void shouldGenerateReport() {
        when(saleReportGenerator.getMostExpensiveSale(anyList())).thenReturn(SaleStub.build().get(0));
        when(customerReportGenerator.getAmountCustomers(anyList())).thenReturn(2L);
        when(salesmanReportGenerator.getAmountSalesmen(anyList())).thenReturn(2L);
        when(salesmanReportGenerator.getWorstSeller(anyList())).thenReturn(SalesmanStub.build().get(1));

        DataReport expected = DataReportStub.build();
        List<RowData> rowData = FileContentStub.getRowData();

        DataReport generated = reportService.generateReport(rowData);

        Assert.assertEquals(expected, generated);
    }
}
