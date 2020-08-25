package com.carlosasrc.dataanalyser.service.report;

import com.carlosasrc.dataanalyser.model.data.Customer;
import com.carlosasrc.dataanalyser.model.data.RowData;
import com.carlosasrc.dataanalyser.model.data.Sale;
import com.carlosasrc.dataanalyser.model.data.Salesman;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class CustomerReportService extends ReportGenerator {
    public Long getAmountCustomers(List<RowData> data) {
        return data.stream()
                .filter(rowData -> rowData.isClass(Customer.class))
                .count();
    }
}
