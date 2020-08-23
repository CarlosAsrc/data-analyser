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
        Boolean hadNewFilesAvailable = dataAnalysisService.processNewFilesMadeAvailable();

        Assert.assertFalse(hadNewFilesAvailable);
        verify(fileProcessorService, never()).processFile(any(File.class));
        verify(fileManager, never()).moveToProcessed(any(File.class));
    }
}
