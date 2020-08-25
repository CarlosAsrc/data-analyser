package com.carlosasrc.dataanalyser.service.report;

import com.carlosasrc.dataanalyser.model.data.RowData;
import com.carlosasrc.dataanalyser.stub.FileContentStub;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class CustomerReportServiceTest {

    @InjectMocks
    private CustomerReportService customerReportService;

    @Test
    public void shouldReturnAmountOfCustomers() {
        List<RowData> rowData = FileContentStub.getRowData();
        Long expected = 2L;
        Long amountCustomers = customerReportService.getAmountCustomers(rowData);

        Assert.assertEquals(expected, amountCustomers);
    }
}
