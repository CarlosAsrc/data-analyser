package com.carlosasrc.dataanalyser.model.file;

import com.carlosasrc.dataanalyser.model.data.Sale;
import com.carlosasrc.dataanalyser.model.data.Salesman;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataReport {
    private Long amountCustomers;
    private Long amountSalesmen;
    private Sale mostExpensiveSale;
    private Salesman worstSeller;

    public String getContent() {
        final String LINE_SEPARATOR = System.getProperty("line.separator");
        return  "Amount of salesmen: "
                .concat(amountSalesmen + LINE_SEPARATOR)
                .concat("Amount of customers: ")
                .concat(amountCustomers + LINE_SEPARATOR)
                .concat("ID of the most expensive sale: ")
                .concat(mostExpensiveSale.getId() + LINE_SEPARATOR)
                .concat("Worst salesman of file: ")
                .concat(worstSeller.getName());
    }
}
