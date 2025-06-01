import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    // retrieve all txt files folder
    public static List<File> listTxtFiles(String folderPath) {
        List<File> txtFiles = new ArrayList<>();
        File folder = new File(folderPath);
        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Le dossier spécifié n'existe pas ou n'est pas un dossier.");
            return txtFiles;
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