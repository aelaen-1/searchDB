import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileUtils {

    // retrieve all txt files folder
    public static List<File> listTxtFiles(String folderPath) {
        List<File> txtFiles = new ArrayList<>();
        File folder = new File(folderPath);
        while (!folder.exists() || !folder.isDirectory()) {
            System.out.println("This folder does not exist, or it's not a directory. " +
                   "Type the path of the folder you want to index :");
            Scanner sc = new Scanner(System.in);
            folderPath = sc.nextLine();
            folder = new File (folderPath);
        }

        File[] allFiles = folder.listFiles();
        if (allFiles != null) {
            for (File f : allFiles) {
                if (f.isFile() && f.getName().toLowerCase().endsWith(".txt")) {
                    txtFiles.add(f);
                }
            }
        }
        return txtFiles;
    }
}