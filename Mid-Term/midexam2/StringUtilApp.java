import java.util.Scanner;

public class StringUtilApp {
    public static void main(String[] args) {
        String text =
		"Java was originally developed by James Gosling at Sun Microsystems." + 
		"It was released in May 1995 as a core component of Sun's Java platform." + 
		"Sun had relicensed most of its Java technologies under the GPL-2.0-only license.";	
        Scanner scanner = new Scanner(System.in);
        StringUtil util = new StringUtil();

        while (true) {
            System.out.println("----------------------");
            System.out.println("1. Search a word");
            System.out.println("2. Replace a word");
            System.out.println("3. Slice a String");
            System.out.println("0. Quit");
            System.out.println("----------------------");
            System.out.print("Choose a number: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            if (choice == 0) {
                System.out.println("Quit Program");
                break;
            } else if (choice == 1) {
                System.out.print("Enter search word: ");
                String searchWord = scanner.nextLine();
                int count = util.getWordCount(text, searchWord);
                System.out.println( searchWord+ " found: " + count + " times");
            } else if (choice == 2) {
                System.out.print("Enter search word: ");
                String searchWord = scanner.nextLine();
                System.out.print("Enter replacement: ");
                String replacement = scanner.nextLine();
                String result = util.replaceWord(text, searchWord, replacement);
                System.out.println("Result: " + result);
            } else if (choice == 3) {
                int start = -1;
                int end = -1;
                System.out.print("Enter start index: ");
                start = scanner.nextInt();
                System.out.print("Enter end index: ");
                end = scanner.nextInt();
                scanner.nextLine();  
                util.sliceString(text, start, end);
            }
        }

        scanner.close();
    }
}