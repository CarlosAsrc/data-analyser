package com.carlosasrc.dataanalyser.service.report;

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
public class SalesmanReportService extends ReportGenerator {

    public Salesman getWorstSeller(List<RowData> data) {
        List<Sale> sales = (List<Sale>) getListByType(data, Sale.class);
        List<Salesman> salesmen = (List<Salesman>) getListByType(data, Salesman.class);
        Map<Salesman, BigDecimal> salesmansSales = salesmansSales(sales, salesmen);
        return Collections.min(salesmansSales.entrySet(), Map.Entry.comparingByValue()).getKey();
    }


    public Long getAmountSalesmen(List<RowData> data) {
        return data.stream()
                .filter(rowData -> rowData.isClass(Salesman.class))
                .count();
    }

    private Map<Salesman, BigDecimal> salesmansSales(List<Sale> salesList, List<Salesman> salesmenList) {
        Map<Salesman, BigDecimal> salesmansSales = new HashMap<>();
        salesmenList.forEach(salesman -> {
            BigDecimal valueSold = salesList.stream()
                    .filter(sale -> sale.getSalesmanName().equals(salesman.getName()))
                    .map(Sale::getSalePrice)
                    .reduce(BigDecimal::add)
                    .orElse(BigDecimal.ZERO);
            salesmansSales.put(salesman, valueSold);
        });
        return salesmansSales;
    }
}
