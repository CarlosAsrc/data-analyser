package com.carlosasrc.dataanalyser.service.report;

import com.carlosasrc.dataanalyser.model.data.RowData;
import com.carlosasrc.dataanalyser.model.data.Sale;
import com.carlosasrc.dataanalyser.model.data.Salesman;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class SaleReportService extends ReportGenerator {
    public Sale getMostExpensiveSale(List<RowData> data) {
        List<Sale> sales = (List<Sale>) getListByType(data, Sale.class);
        return Collections.max(sales, Comparator.comparing(Sale::getSalePrice));
    }
}
