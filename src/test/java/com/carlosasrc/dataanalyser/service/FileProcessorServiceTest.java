package com.carlosasrc.dataanalyser.service;

import com.carlosasrc.dataanalyser.io.FileManager;
import com.carlosasrc.dataanalyser.model.file.DataReport;
import com.carlosasrc.dataanalyser.parser.FileParser;
import com.carlosasrc.dataanalyser.properties.IOProperties;
import com.carlosasrc.dataanalyser.service.report.ReportService;
import com.carlosasrc.dataanalyser.stub.DataReportStub;
import com.carlosasrc.dataanalyser.stub.FileContentStub;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class FileProcessorServiceTest {

    @InjectMocks
    private FileProcessorService fileProcessorService;

    @Mock
    private FileManager fileManager;
    @Mock
    private ReportService reportService;
    @Mock
    private FileParser fileParser;
    @Mock
    private IOProperties ioProperties;

    private File file;
    private final DataReport dataReport = DataReportStub.build();

    @Before
    public void setUp() throws IOException {
        file = File.createTempFile("test", null);
        file.deleteOnExit();

        when(fileManager.getFileContent(any())).thenReturn(FileContentStub.getFullFileContent());
        when(fileParser.parse(any())).thenReturn(FileContentStub.getRowData());
        DataReport dataReport = DataReportStub.build();
        when(reportService.generateReport(any())).thenReturn(dataReport);
    }

    @Test
    public void shouldProcessFile() {
        fileProcessorService.processFile(file);
        verify(fileManager).saveReport(eq(dataReport), any(), any());
    }

    @Test
    public void shouldSummarizeProcessedFiles() {
        fileProcessorService.summarizeProcessedFiles(Collections.singletonList(file));
        verify(fileManager).saveReport(eq(dataReport), any(), any());
    }

}
