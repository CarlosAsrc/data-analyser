package com.carlosasrc.dataanalyser.service;

import com.carlosasrc.dataanalyser.io.FileManager;
import com.carlosasrc.dataanalyser.properties.IOProperties;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class DataAnalysisServiceTest {

    @InjectMocks
    private DataAnalysisService dataAnalysisService;

    @Mock
    private FileProcessorService fileProcessorService;

    @Mock
    private FileManager fileManager;

    @Mock
    private IOProperties ioProperties;

    @Test
    public void shouldDoNothingWithFilesNotMadeAvailable() {
        when(fileManager.getValidFilesFromDirectory(any())).thenReturn((Collections.emptyList()));
        Boolean hadNewFilesAvailable = dataAnalysisService.processNewFilesMadeAvailable();

        Assert.assertFalse(hadNewFilesAvailable);
        verify(fileProcessorService, never()).processFile(any(File.class));
        verify(fileManager, never()).moveToProcessed(any(File.class));
    }

    @Test
    public void shouldProcessFilesNotMadeAvailable() throws IOException {
        File file = File.createTempFile("test", null);
        file.deleteOnExit();
        when(fileManager.getValidFilesFromDirectory(any())).thenReturn((Collections.singletonList(file)));
        Boolean hadNewFilesAvailable = dataAnalysisService.processNewFilesMadeAvailable();

        Assert.assertTrue(hadNewFilesAvailable);
        verify(fileProcessorService).processFile(any(File.class));
        verify(fileManager).moveToProcessed(any(File.class));
    }

    @Test
    public void shouldDoNothingWhenThrowsRuntimeExceptionOnReadFiles() {
        when(fileManager.getValidFilesFromDirectory(any())).thenThrow(RuntimeException.class);
        Boolean hadNewFilesAvailable = dataAnalysisService.processNewFilesMadeAvailable();

        Assert.assertFalse(hadNewFilesAvailable);
        verify(fileProcessorService, never()).processFile(any(File.class));
        verify(fileManager, never()).moveToProcessed(any(File.class));
    }

    @Test
    public void shouldGenerateSummarizedReport() throws IOException {
        File file = File.createTempFile("test", null);
        file.deleteOnExit();
        List<File> files = Collections.singletonList(file);
        when(fileManager.getValidFilesFromDirectory(any())).thenReturn((files));
        dataAnalysisService.generateSummarizedReport();

        verify(fileProcessorService).summarizeProcessedFiles(files);
    }

    @Test
    public void shouldNotGenerateSummarizedReportWhenThrowsRuntimeExceptionOnReadFiles() {
        when(fileManager.getValidFilesFromDirectory(any())).thenThrow(RuntimeException.class);
        dataAnalysisService.generateSummarizedReport();

        verify(fileProcessorService, never()).summarizeProcessedFiles(any());
    }
}
