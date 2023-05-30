package com.solvd.uniquewordcounter;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UniqueFileWords {

    private static final String FILE_PATH = "src/main/java/com/solvd/lab2/uniquewordcounter/TuringMachine.txt";
    private static final String RESULT_PATH = "src/main/java/com/solvd/lab2/uniquewordcounter/result.txt";

    public static void uniqueList() throws IOException {
        Logger fileLogger = LogManager.getLogger(UniqueFileWords.class);
        File Read = FileUtils.getFile(FILE_PATH);
        File Write = FileUtils.getFile(RESULT_PATH);

        try {
            String contents = FileUtils.readFileToString(Read, StandardCharsets.UTF_8);
            String[] allWords = StringUtils.split(contents, "[^a-zA-Z0-9']");
            Set<String> uniqueWords = new HashSet<String>();


            for (String word : allWords) {
                uniqueWords.add(word);
            }

            String toWrite = "Unique words found among text: " + uniqueWords.size() + "\n";

            fileLogger.info(toWrite);
            FileUtils.writeStringToFile(Write, toWrite, StandardCharsets.UTF_8, true);

        } catch (IOException ioe) {
            fileLogger.error(ioe.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        uniqueList();
    }
}