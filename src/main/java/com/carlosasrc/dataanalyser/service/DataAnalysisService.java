package com.carlosasrc.dataanalyser.service;

import com.carlosasrc.dataanalyser.io.FileManager;
import com.carlosasrc.dataanalyser.properties.IOProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Slf4j
@Service
public class DataAnalysisService {

    private final FileProcessorService fileProcessorService;
    private final FileManager fileManager;
    private final IOProperties ioProperties;

    public DataAnalysisService(FileProcessorService fileProcessorService, FileManager fileManager, IOProperties ioProperties) {
        this.fileProcessorService = fileProcessorService;
        this.fileManager = fileManager;
        this.ioProperties = ioProperties;
        fileManager.createDirectories();
    }

    public Boolean processNewFilesMadeAvailable() {
        try {
            log.info("Searching for new files on input directory...");
            List<File> files = fileManager.getValidFilesFromDirectory(ioProperties.getInputDirectory());

            if(files.isEmpty())
                return Boolean.FALSE;

            files.forEach(file -> {
                log.info("Starting process {} file", file.getName());
                fileProcessorService.processFile(file);
                fileManager.moveToProcessed(file);
                log.info("Finishing process {} file", file.getName());
            });
            return Boolean.TRUE;
        } catch (Exception e) {
            log.error("Error processing input files: ", e);
            return Boolean.FALSE;
        }
    }

    public void generateSummarizedReport() {
        try {
            log.info("Generating Summarized Report considering all input files");
            List<File> filesProcessed = fileManager.getValidFilesFromDirectory(ioProperties.getProcessedDirectory());
            fileProcessorService.summarizeProcessedFiles(filesProcessed);
            log.info("Summarized Report generation has been completed");
        } catch (Exception e) {
            log.error("Error generating Summarized Report: ", e);
        }
    }
}
