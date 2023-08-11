package logreader;

import logreader.action.Results;
import logreader.util.LogParser;
import logreader.util.Top3Finder;

import java.nio.file.Path;
import java.nio.file.Paths;

public class App {
    private static final LogParser logParser = new LogParser();
    private static final Top3Finder top3Finder = new Top3Finder();
    private static final Results results = new Results(logParser, top3Finder);

    public static void main(String[] args) {
        //Update file path to your required log file if needed
        String filePath = "/Users/william/IdeaProjects/Java/logReader/app/src/main/resources/programming-task-example-data.log";

        //Returns results of the required tasks
        results.result(filePath);

        String filename="programming-task-example-data.log";
        Path pathToFile = Paths.get(filename);
        System.out.println(pathToFile.toAbsolutePath());
    }}
