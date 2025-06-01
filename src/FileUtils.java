import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    public static List<File> listerFichiersTxt(String dossierPath) {
        List<File> fichiersTxt = new ArrayList<>();
        File dossier = new File(dossierPath);
        if (!dossier.exists() || !dossier.isDirectory()) {
            System.out.println("Le dossier spécifié n'existe pas ou n'est pas un dossier.");
            return fichiersTxt;
        }

        File[] fichiers = dossier.listFiles();
        if (fichiers != null) {
            for (File f : fichiers) {
                if (f.isFile() && f.getName().toLowerCase().endsWith(".txt")) {
                    fichiersTxt.add(f);
                }
            }
        }
        return fichiersTxt;
    }
}