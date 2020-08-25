package com.carlosasrc.dataanalyser.service.report;

import com.carlosasrc.dataanalyser.model.data.RowData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public abstract class ReportGenerator {
    public List<?> getListByType(List<RowData> data, Class<?> classType) {
        return data.stream()
                .filter(rowData -> rowData.isClass(classType))
                .collect(Collectors.toList());
    }
}
