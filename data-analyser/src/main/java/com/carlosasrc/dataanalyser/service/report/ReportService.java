package com.carlosasrc.dataanalyser.service.report;

import com.carlosasrc.dataanalyser.model.data.RowData;
import com.carlosasrc.dataanalyser.model.file.DataReport;
import com.carlosasrc.dataanalyser.service.report.generator.CustomerReportGenerator;
import com.carlosasrc.dataanalyser.service.report.generator.SaleReportGenerator;
import com.carlosasrc.dataanalyser.service.report.generator.SalesmanReportGenerator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ReportService {

    private final SalesmanReportGenerator salesmanReportGenerator;
    private final CustomerReportGenerator customerReportGenerator;
    private final SaleReportGenerator saleReportGenerator;

    public DataReport generateReport(List<RowData> data) {
        return DataReport.builder()
                .amountCustomers(customerReportGenerator.getAmountCustomers(data))
                .amountSalesmen(salesmanReportGenerator.getAmountSalesmen(data))
                .mostExpensiveSale(saleReportGenerator.getMostExpensiveSale(data))
                .worstSeller(salesmanReportGenerator.getWorstSeller(data))
                .build();
    }
}
