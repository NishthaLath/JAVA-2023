import java.io.*;
import java.util.*;

public class WordSearch {
    public static void main(String[] args) {
        List<String> lines = new ArrayList<>();
        
        // Reading the file and storing lines in the list
        try (BufferedReader br = new BufferedReader(new FileReader("java.txt"))) {
            String line;
            int lineNumber = 1;
            while ((line = br.readLine()) != null) {
                lines.add(lineNumber + "\t" + line);
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Type a word to search >> ");
            String searchWord = scanner.nextLine();
            if (searchWord.equalsIgnoreCase("quit")) {
                System.out.println("Exit Program!");
                break;
            }

            boolean found = false;
            for (String line : lines) {
                if (line.toLowerCase().contains(searchWord.toLowerCase())) {
                    System.out.println(line);
                    found = true;
                }
            }

            if (!found) {
                System.out.println();
            }
        }
        scanner.close();
    }
}
