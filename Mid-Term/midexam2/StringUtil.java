public class StringUtil {
    public int getWordCount(String text, String searchWord) {
        int count = 0;
        int index = 0;

        System.out.print("index: [");
        while ((index = text.indexOf(searchWord, index)) != -1) {
            System.out.print(index + ", ");
            count++;
            index += searchWord.length();
        }
        System.out.println("]");
        System.out.println();
        
        if (count == 0) {
            System.out.println(searchWord + " is not found!");
            System.out.println();
        }
        return count;
    }

    public String replaceWord(String text, String searchWord, String replacement) {
        if (!text.contains(searchWord)) {
            System.out.println(searchWord+ " is not found!");
            System.out.println();
            return "";
        }
        return text.replace(searchWord, replacement);
    }

    public void sliceString(String text, int start, int end) {
        System.out.println("start: " + start + ", end: "+ end+ ", len: " + text.length());

        if(end > text.length()){
            System.out.println("Index is out of range!");
        }
        else{
            System.out.println("Sliced String: "+text.substring(start, end));
        }
    }
}