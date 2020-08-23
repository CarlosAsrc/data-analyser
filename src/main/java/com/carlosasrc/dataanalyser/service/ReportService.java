package com.carlosasrc.dataanalyser.service;

import com.carlosasrc.dataanalyser.model.data.Customer;
import com.carlosasrc.dataanalyser.model.data.RowData;
import com.carlosasrc.dataanalyser.model.data.Sale;
import com.carlosasrc.dataanalyser.model.data.Salesman;
import com.carlosasrc.dataanalyser.model.file.DataReport;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ReportService {
    public DataReport generateReport(List<RowData> data) {
        return DataReport.builder()
                .amountCustomers(getAmountCustomers(data))
                .amountSalesmen(getAmountSalesmen(data))
                .mostExpensiveSale(getMostExpensiveSale(data))
                .worstSeller(getWorstSeller(data))
                .build();
    }

    private Long getAmountCustomers(List<RowData> data) {
        return data.stream()
                .filter(rowData -> rowData.isClass(Customer.class))
                .count();
    }

    private Long getAmountSalesmen(List<RowData> data) {
        return data.stream()
                .filter(rowData -> rowData.isClass(Salesman.class))
                .count();
    }

    private Sale getMostExpensiveSale(List<RowData> data) {
        List<Sale> sales = getSalesList(data);
        return Collections.max(sales, Comparator.comparing(Sale::getSalePrice));
    }

    private Salesman getWorstSeller(List<RowData> data) {
        Map<Salesman, BigDecimal> salesmansSales = salesmansSales(getSalesList(data), getSalesmenList(data));
        return Collections.min(salesmansSales.entrySet(), Map.Entry.comparingByValue()).getKey();
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


    private List<Sale> getSalesList(List<RowData> data) {
        return data.stream()
                .filter(rowData -> rowData.isClass(Sale.class))
                .map(rowData -> (Sale) rowData)
                .collect(Collectors.toList());
    }

    private List<Salesman> getSalesmenList(List<RowData> data) {
        return data.stream()
                .filter(rowData -> rowData.isClass(Salesman.class))
                .map(rowData -> (Salesman) rowData)
                .collect(Collectors.toList());
    }
}
