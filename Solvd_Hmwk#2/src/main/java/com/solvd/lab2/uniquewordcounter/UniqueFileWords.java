package com.solvd.lab2.uniquewordcounter;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UniqueFileWords {

    static File fileList = new File("src/main/java/com/solvd/lab2/uniquewordcounter/listofnames.txt");
    static File fileResult = new File("src/main/java/com/solvd/lab2/uniquewordcounter/result.txt");

    String textList;

    static UniqueFileWords unique = null;
    private static final Logger fileLogger = LogManager.getLogger(UniqueFileWords.class);

    public UniqueFileWords(String textList) {
        this.textList = textList;
    }

    public void uniqueList() throws IOException {
        FileUtils.writeStringToFile(fileList, textList);
        FileUtils.readFileToString(fileList, StandardCharsets.UTF_8.name());

        String[] names = StringUtils.split(textList, ","); //spearate each word in text file by comma
        int uniqueresult = 0;

        for(int i=0; i < names.length; i++) {
            //true if the word starts with *
            if(StringUtils.startsWith(names[i], "*")){
                uniqueresult++;
            }
        }

        FileUtils.writeStringToFile(fileResult, String.valueOf(uniqueresult), StandardCharsets.UTF_8.name());

        fileLogger.info("Of the list of possible components: " + textList);
        fileLogger.info("There is a total of " + uniqueresult + " unique names that we can test.");
    }

    public static void main(String[] args) throws IOException {
        unique = new UniqueFileWords("*Screen, Motherboard, CPU, *HardDrive, Speakers, *USBAdapter, *PowerUnit, *Fan, Memory, DataStorage");
        unique.uniqueList();
    }
}