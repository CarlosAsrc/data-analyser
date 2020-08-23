package com.carlosasrc.dataanalyser.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ParsingProperties {

    @Value("${application.data.manipulation.parsing.splitter.row-data-splitter}")
    private String rowDataSplitter;

    @Value("${application.data.manipulation.parsing.splitter.item-splitter}")
    private String itemSplitter;

    @Value("${application.data.manipulation.parsing.splitter.item-list-splitter}")
    private String itemListSplitter;

    @Value("${application.data.manipulation.parsing.index.salesman.cpf}")
    private Integer salesmanCpfIndex;

    @Value("${application.data.manipulation.parsing.index.salesman.name}")
    private Integer salesmanNameIndex;

    @Value("${application.data.manipulation.parsing.index.salesman.salary}")
    private Integer salesmanSalaryIndex;

    @Value("${application.data.manipulation.parsing.index.customer.cnpj}")
    private Integer customerCnpjIndex;

    @Value("${application.data.manipulation.parsing.index.customer.name}")
    private Integer customerNameIndex;

    @Value("${application.data.manipulation.parsing.index.customer.business-area}")
    private Integer customerBusinessAreaIndex;

    @Value("${application.data.manipulation.parsing.index.sale.id}")
    private Integer saleIdIndex;

    @Value("${application.data.manipulation.parsing.index.sale.item}")
    private Integer saleItemsIndex;

    @Value("${application.data.manipulation.parsing.index.sale.name-salesman}")
    private Integer saleNameSalesmanIndex;

    @Value("${application.data.manipulation.parsing.index.item.id}")
    private Integer itemIdIndex;

    @Value("${application.data.manipulation.parsing.index.item.quantity}")
    private Integer itemQuantityIndex;

    @Value("${application.data.manipulation.parsing.index.item.price}")
    private Integer itemPriceIndex;

    @Value("${application.data.manipulation.parsing.regex.brackets}")
    private String bracketsRegex;

    @Value("${application.data.manipulation.parsing.regex.salesman}")
    private String salesmanRegex;

    @Value("${application.data.manipulation.parsing.regex.customer}")
    private String customerRegex;

    @Value("${application.data.manipulation.parsing.regex.sale}")
    private String saleRegex;

    @Value("${application.data.manipulation.parsing.row-type-id.salesman}")
    private String salesmanFormatId;

    @Value("${application.data.manipulation.parsing.row-type-id.customer}")
    private String customerFormatId;

    @Value("${application.data.manipulation.parsing.row-type-id.sale}")
    private String saleFormatId;

}
