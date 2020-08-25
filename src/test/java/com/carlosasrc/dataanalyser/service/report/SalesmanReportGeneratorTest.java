package com.carlosasrc.dataanalyser.service.report;

import com.carlosasrc.dataanalyser.model.data.RowData;
import com.carlosasrc.dataanalyser.model.data.Salesman;
import com.carlosasrc.dataanalyser.service.report.generator.SalesmanReportGenerator;
import com.carlosasrc.dataanalyser.stub.FileContentStub;
import com.carlosasrc.dataanalyser.stub.SalesmanStub;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class SalesmanReportGeneratorTest {

    @InjectMocks
    private SalesmanReportGenerator salesmanReportGenerator;

    @Test
    public void shouldReturnAmountOfCustomers() {
        List<RowData> rowData = FileContentStub.getRowData();
        Long expected = 2L;
        Long amountSalesmen = salesmanReportGenerator.getAmountSalesmen(rowData);

        Assert.assertEquals(expected, amountSalesmen);
    }

    @Test
    public void shouldReturnWorstSalesman() {
        List<RowData> rowData = FileContentStub.getRowData();
        Salesman expected = SalesmanStub.build().get(1);
        Salesman  worstSeller = salesmanReportGenerator.getWorstSeller(rowData);

        Assert.assertEquals(expected, worstSeller);
    }
}
