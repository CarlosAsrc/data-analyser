package com.carlosasrc.dataanalyser.io;

import com.carlosasrc.dataanalyser.model.DataReport;
import com.carlosasrc.dataanalyser.properties.IOProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Component
@AllArgsConstructor
public class FileManager {

    private final IOProperties ioProperties;

    public List<File> getValidFilesFromDirectory(String inputDirectory) {
        try (Stream<Path> paths = Files.walk(Paths.get(inputDirectory))) {
            return paths
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .filter(this::filterAllowedFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void saveReport(DataReport report, String directory, String inputFileName) {
        final String reportFileName = getReportFileName(inputFileName);
        final Path filePath = Paths.get(directory, reportFileName);
        writeReport(report, filePath);
    }


    private void writeReport(DataReport report, Path filePath) {
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            writer.write(report.toString());
            log.info("Report from {} file saved in {}", filePath.getFileName(), filePath);
        } catch (IOException e) {
            log.error("Error writing report from {}} file", filePath.getFileName(), e);
            throw new RuntimeException(e.getMessage());
        }
    }


    public List<String> getFileContent(final File file) {
        try {
            return Files.readAllLines(file.toPath(), Charset.defaultCharset());
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public void moveToProcessed(File file) {
        try {
            Files.move(
                    buildPath(ioProperties.getInputDirectory(), file.getName()),
                    buildPath(ioProperties.getProcessedDirectory(), file.getName()),
                    StandardCopyOption.REPLACE_EXISTING
            );
            log.info("File {} moved to {}/{}", file.getName(), ioProperties.getProcessedDirectory(),file.getName());
        } catch (IOException e) {
            log.error("Error when moving file {} from the input directory to the processed file directory.", file.getName(), e);
            e.printStackTrace();
        }
    }


    private boolean filterAllowedFile(File file) {
        return file.getName().toLowerCase().endsWith(ioProperties.getInputFileExtension());
    }


    public Path buildPath(String directory, String fileName) {
        return Paths.get(String.format("%s/%s",directory,fileName));
    }


    public void createDirectories() {
        Stream.of(
                ioProperties.getInputDirectory(),
                ioProperties.getOutputDirectory(),
                ioProperties.getProcessedDirectory(),
                ioProperties.getIndividualReportsDirectory())
                .map(File::new)
                .forEach(this::createDirectory);
    }


    private void createDirectory(File file) {
        if (!file.exists() && !file.mkdirs()) {
            throw new RuntimeException(String.format("Error creating %s diretory:", file));
        }
        log.info("{} directory configured!", file);
    }


    private String getReportFileName(String inputFileName) {
        return inputFileName.replaceAll(ioProperties.getInputFileExtension(), "") + ioProperties.getOutputFileExtension();
    }
}
