package com.carlosasrc.dataanalyser.service.report.generator;

import com.carlosasrc.dataanalyser.model.data.RowData;
import com.carlosasrc.dataanalyser.model.data.Sale;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@AllArgsConstructor
public class SaleReportGenerator extends ReportGenerator {
    public Sale getMostExpensiveSale(List<RowData> data) {
        List<Sale> sales = (List<Sale>) getListByType(data, Sale.class);
        return Collections.max(sales, Comparator.comparing(Sale::getSalePrice));
    }
}
