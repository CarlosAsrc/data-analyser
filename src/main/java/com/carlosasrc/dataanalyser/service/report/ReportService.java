package com.carlosasrc.dataanalyser.service.report;

import com.carlosasrc.dataanalyser.model.data.RowData;
import com.carlosasrc.dataanalyser.model.file.DataReport;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ReportService {

    private final SalesmanReportService salesmanReportService;
    private final CustomerReportService customerReportService;
    private final SaleReportService saleReportService;

    public DataReport generateReport(List<RowData> data) {
        return DataReport.builder()
                .amountCustomers(customerReportService.getAmountCustomers(data))
                .amountSalesmen(salesmanReportService.getAmountSalesmen(data))
                .mostExpensiveSale(saleReportService.getMostExpensiveSale(data))
                .worstSeller(salesmanReportService.getWorstSeller(data))
                .build();
    }
}
