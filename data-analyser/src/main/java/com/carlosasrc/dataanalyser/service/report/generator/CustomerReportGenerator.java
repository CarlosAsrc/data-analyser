package com.carlosasrc.dataanalyser.service.report.generator;

import com.carlosasrc.dataanalyser.model.data.Customer;
import com.carlosasrc.dataanalyser.model.data.RowData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CustomerReportGenerator extends ReportGenerator {
    public Long getAmountCustomers(List<RowData> data) {
        return data.stream()
                .filter(rowData -> rowData.isClass(Customer.class))
                .count();
    }
}
