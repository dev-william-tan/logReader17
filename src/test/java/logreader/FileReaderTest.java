package logreader;

import logreader.service.FileReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileReaderTest {

    FileReader fileReader = new FileReader();
    @Test
    public void testFileReader_Success() throws IOException {

        Path tempFile = createTempFile();

        Stream<String> actual = fileReader.fileReader(tempFile);

        assertEquals(2, actual.count());

        Files.delete(tempFile);
    }

    private Path createTempFile() throws IOException {
        Path tempFilePath = Files.createTempFile("testFile", ".log");
        Files.write(tempFilePath, "test 1 \n test 2 \n".getBytes());
        return tempFilePath;
    }


}
