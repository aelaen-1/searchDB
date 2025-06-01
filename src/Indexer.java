import java.io.BufferedReader;
import java.io.File;
import java.nio.file.Files;
import java.io.IOException;
import java.util.*;

public class Indexer {
    // <word, <filename, word occurences>>
    private Map<String, Map<String, Integer>> index = new HashMap<>();

    // index all files in folder
    public void indexFolder(String folderPath){
        List<File> txtFiles = FileUtils.listTxtFiles(folderPath);

        for (File file : txtFiles){
            indexFile(file);
        }
    }
    //index a single file
    private void indexFile(File file) {
        try {
            List<String> lines = Files.readAllLines(file.toPath());

            for (String line : lines) {
                // "\\": non alphanumeric char, "+" : one or many chars
                String[] words = line.toLowerCase().split("\\W+");

                for (String word : words) {
                    if (word.isEmpty()) continue;
                    // filemap is <filename, occurences>
                    // index.getordefautl() return the <filename, occurence> in index
                    Map<String, Integer> fileMap = index.getOrDefault(word, new HashMap<>());
                    // return the occurence in <filename, occurence>
                    int count = fileMap.getOrDefault(file.getName(), 0);
                    fileMap.put(file.getName(), count + 1);
                    index.put(word, fileMap);
                }
            }

        } catch (IOException e) {
            System.out.println("Error: cannot read file " + file.getName());
        }
    }

    public void searchWord(String word){
        word = word.toLowerCase();
        if (!index.containsKey(word)){
            System.out.println("Can't find word '" + word + "' in indexed files.");
            return ;
        }
        Map<String, Integer> fileMap = index.get(word);
        // converts map to list, to sort them according to the nb of occurences
        List<Map.Entry<String,Integer>> sortedFiles = new ArrayList<>(fileMap.entrySet());
        sortedFiles.sort((a, b) -> b.getValue() - a.getValue());

        System.out.println("Results for '" + word + "':");
        for (Map.Entry<String, Integer> entry : sortedFiles){
            System.out.println(entry.getKey() + " : " + entry.getValue() + " occurence(s)");
        }
    }
}