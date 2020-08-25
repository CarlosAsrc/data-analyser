package com.carlosasrc.dataanalyser.io;

import com.carlosasrc.dataanalyser.properties.IOProperties;
import com.carlosasrc.dataanalyser.stub.DataReportStub;
import com.carlosasrc.dataanalyser.stub.FileContentStub;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class FileManagerTest {

    @InjectMocks
    private FileManager fileManager;

    @Mock
    private IOProperties ioProperties;

    private File tmpFile;
    private Path tmpTestDir;
    private Path inDir;
    private Path outDir;
    private Path processedDir;
    private Path individualReportDir;


    @Before
    public void setUp() throws IOException {
        tmpTestDir = Files.createTempDirectory( "test-");
        inDir = Files.createTempDirectory( tmpTestDir, "IN-");
        outDir = Files.createTempDirectory( tmpTestDir, "OUT-");
        processedDir = Files.createTempDirectory( tmpTestDir, "PROCESSED-");
        individualReportDir = Files.createTempDirectory( tmpTestDir, "individual-reports-");
    }


    @After
    public void destroy() throws IOException {
        Files.walk(tmpTestDir)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
    }


    @Test
    public void shouldGetValidFilesFromDirectory() throws IOException {
        createTestFile(tmpTestDir.toFile());
        when(ioProperties.getInputFileExtension()).thenReturn(".dat");
        List<File> files = fileManager.getValidFilesFromDirectory(tmpTestDir.toString());
        Assert.assertEquals(Collections.singletonList(tmpFile), files);
    }

    @Test
    public void shouldCreateDirectories() {
        String inDir = tmpTestDir.toString()+"/IN";
        String outDir = tmpTestDir.toString()+"/OUT";
        String processedDir = tmpTestDir.toString()+"/PROCESSED";
        String individualReportsDir = tmpTestDir.toString()+"/individual-reports";

        when(ioProperties.getInputDirectory()).thenReturn(inDir);
        when(ioProperties.getOutputDirectory()).thenReturn(outDir);
        when(ioProperties.getProcessedDirectory()).thenReturn(processedDir);
        when(ioProperties.getIndividualReportsDirectory()).thenReturn(individualReportsDir);

        fileManager.createDirectories();

        Assert.assertTrue(Files.exists(Paths.get(inDir)));
        Assert.assertTrue(Files.exists(Paths.get(outDir)));
        Assert.assertTrue(Files.exists(Paths.get(processedDir)));
        Assert.assertTrue(Files.exists(Paths.get(individualReportsDir)));
    }


    @Test
    public void shouldMoveFileToprocessed() throws IOException {
        when(ioProperties.getInputDirectory()).thenReturn(inDir.toString());
        when(ioProperties.getProcessedDirectory()).thenReturn(processedDir.toString());

        createTestFile(Paths.get(inDir.toString()).toFile());

        fileManager.moveToProcessed(tmpFile);

        Assert.assertTrue(Files.exists(Paths.get(processedDir+"/"+ tmpFile.getName())));
    }

    @Test
    public void shouldSaveFile() {
        when(ioProperties.getInputFileExtension()).thenReturn(".dat");
        when(ioProperties.getOutputFileExtension()).thenReturn(".done.dat");
        fileManager.saveReport(DataReportStub.build(), outDir.toString(), "test");
    }


    private void createTestFile(File dir) throws IOException {
        tmpFile = File.createTempFile("test",".dat", dir);
        BufferedWriter writer = Files.newBufferedWriter(tmpFile.toPath());
        final String LINE_SEPARATOR = System.getProperty("line.separator");
        for(String line: FileContentStub.getFullFileContent()) {
            writer.write(line+LINE_SEPARATOR);
        }
        writer.close();
    }

}
