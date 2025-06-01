import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Type the path of the folder you want to index :");
        String  folderPath = sc.nextLine();

        Indexer indexer = new Indexer();
        indexer.indexFolder(folderPath);

        while (true){
            System.out.println("Type the word you are looking for (or 'exit'):");
            String  query = sc.nextLine().trim();
            if (query.equalsIgnoreCase("exit")){
                break;
            }
            indexer.searchWord(query);
        }
        sc.close();
    }
}