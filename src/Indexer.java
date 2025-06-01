import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Indexer {

    private Map<String, Map<String, Integer>> index = new HashMap<>();

    public void indexerDossier(String dossierPath) {
        List<File> fichiersTxt = FileUtils.listerFichiersTxt(dossierPath);

        for (File fichier : fichiersTxt) {
            indexerFichier(fichier);
        }
    }

    private void indexerFichier(File fichier) {
        try (BufferedReader br = new BufferedReader(new FileReader(fichier))) {
            String ligne;
            while ((ligne = br.readLine()) != null) {
                String[] mots = ligne.toLowerCase().split("\\W+");
                for (String mot : mots) {
                    if (mot.isEmpty()) continue;

                    Map<String, Integer> fichiersMap = index.getOrDefault(mot, new HashMap<>());
                    int count = fichiersMap.getOrDefault(fichier.getName(), 0);
                    fichiersMap.put(fichier.getName(), count + 1);
                    index.put(mot, fichiersMap);
                }
            }
        } catch (IOException e) {
            System.out.println("Erreur lecture fichier : " + fichier.getName());
        }
    }

    public void rechercherMot(String mot) {
        mot = mot.toLowerCase();
        if (!index.containsKey(mot)) {
            System.out.println("Mot '" + mot + "' non trouvé dans les fichiers indexés.");
            return;
        }

        Map<String, Integer> fichiersMap = index.get(mot);

        List<Map.Entry<String, Integer>> sortedFiles = new ArrayList<>(fichiersMap.entrySet());
        sortedFiles.sort((a, b) -> b.getValue() - a.getValue());

        System.out.println("Résultats pour '" + mot + "' :");
        for (Map.Entry<String, Integer> entry : sortedFiles) {
            System.out.println("  " + entry.getKey() + " : " + entry.getValue() + " occurrence(s)");
        }
    }
}