package com.carlosasrc.dataanalyser.service;

import com.carlosasrc.dataanalyser.io.FileManager;
import com.carlosasrc.dataanalyser.model.file.DataReport;
import com.carlosasrc.dataanalyser.model.data.RowData;
import com.carlosasrc.dataanalyser.parser.FileParser;
import com.carlosasrc.dataanalyser.properties.IOProperties;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@AllArgsConstructor
public class FileProcessorService {

    private final FileManager fileManager;
    private final ReportService reportService;
    private final FileParser fileParser;
    private final IOProperties ioProperties;

    public void processFile(File file) {
        Stream.of(file)
                .map(fileManager::getFileContent)
                .map(fileParser::parse)
                .map(reportService::generateReport)
                .forEach(report -> {
                    fileManager.saveReport(report, ioProperties.getIndividualReportsDirectory(), file.getName());
                });
    }

    public void summarizeProcessedFiles(List<File> filesProcessed) {
        List<RowData> rowData = filesProcessed.stream()
                .map(fileManager::getFileContent)
                .map(fileParser::parse)
                .flatMap(List::stream)
                .distinct()
                .collect(Collectors.toList());
        DataReport dataReport = reportService.generateReport(rowData);
        fileManager.saveReport(dataReport, ioProperties.getOutputDirectory(), ioProperties.getSummarizedReportFileName());
    }

}
