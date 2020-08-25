package com.carlosasrc.dataanalyser.model.file;

import com.carlosasrc.dataanalyser.model.data.RowData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class InputFileContent {
    private String fileName;
    private List<String> listLines;
    private List<RowData> rowData;
}
