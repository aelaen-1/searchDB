import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrez le chemin du dossier à indexer :");
        String folderPath = sc.nextLine();

        Indexer indexer = new Indexer();
        indexer.indexerDossier(folderPath);

        System.out.println("Indexation terminée !");

        while (true) {
            System.out.println("Tapez un mot à rechercher (ou 'exit' pour quitter) :");
            String query = sc.nextLine().trim();
            if (query.equalsIgnoreCase("exit")) {
                break;
            }
            indexer.rechercherMot(query);
        }
        sc.close();
    }
}