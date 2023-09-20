import lineParser.ApacheLineParser;
import log.LogParser;
import log.ResultOutput;
import service.FilePathProvider;
import service.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.stream.Stream;

public class App {
    private static final Logger logger = LogManager.getLogger(App.class);
     public static void main(String[] args) throws IOException {
         Scanner scanner = new Scanner(System.in);
         FilePathProvider filePathProvider = new FilePathProvider();
         FileReader fileReader = new FileReader();
         ApacheLineParser apacheLineParser = new ApacheLineParser();
         LogParser logParser = new LogParser(apacheLineParser::parseLogEntry);
         ResultOutput resultOutput = new ResultOutput();

         Path filePath = filePathProvider.getFilePath("programming-task-example-data.log");
         Stream<String> fileStream = fileReader.fileReader(filePath);

         var logMetrics = logParser.logParser(fileStream);

         while (true) {
             System.out.println("Enter the size of the result (or 'q' to exit): ");
             String input = scanner.nextLine();

             if (input.equalsIgnoreCase("q")) {
                 System.out.println("Exiting the app.");
                 break;
             }

             try {
                 int size = Integer.parseInt(input);
                 logger.info("Log Parser app results for " + size);
                 resultOutput.printResults(logMetrics, size);
             } catch (Exception e) {
                 System.out.println("Invalid Input, Please enter a number or q");
             }
         }
         scanner.close();
    }
}
