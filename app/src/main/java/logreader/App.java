package logreader;

import logreader.action.Results;
import logreader.util.LogParser;
import logreader.util.Top3Finder;

public class App {
    private static final LogParser logParser = new LogParser();
    private static final Top3Finder top3Finder = new Top3Finder();
    private static final Results results = new Results(logParser, top3Finder);

    public static void main(String[] args) {

        String filePath = "/Users/william/IdeaProjects/Java/logReader/app/src/main/resources/programming-task-example-data.log";

        results.result(filePath);
    }}
