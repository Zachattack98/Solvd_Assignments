package computerrepairservice;

import java.io.File;
import java.nio.charset.StandardCharsets;


import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

public class UniqueFileWords {
    
    static File fileList = new File("src/computerrepairservice/resources/listofnames.txt");
    static File fileResult = new File("src/computerrepairservice/resources/result.txt");
    
    String textList;

    public UniqueFileWords(String textList) {
        this.textList = textList;
    }
    
    public void uniqueList() {
        FileUtils.writeStringToFile(fileList, textList);
        FileUtils.readFileToString(fileList, StandardCharsets.UTF_8.name());
        
        String[] names = StringUtils.split(textList, ","); //spearate each word in text file by comma
        int uniqueresult = 0;
        
        for(int i=0; i < names.length; i++) {
            //true if the word starts with *
            if(StringUtils.startsWith(names, "*")){ 
                uniqueresult++;
            }
        }
        
        FileUtils.writeStringToFile(fileResult, String.valueOf(uniqueresult), StandardCharsets.UTF_8.name());
    
        System.out.println("Of the list of possible components: " + textList);
        System.out.println("There is a total of " + uniqueresult + " unique names that we can test.");
        System.out.println();
    }

}