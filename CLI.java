import static mandarin.Conversion.*;
import static mandarin.Cedict.*;

public class CLI {

    // javac -encoding "UTF-8" MyFrame.java

    static final int NUM_ENTRIES = Cedict.entries.size();
    
    public static <T> void print(T val) {
        System.out.println(val);
    }

    public static void main(String[] args) {
        printGRMatches("chau ji shy chang");

        printMatches("freedom");

        printExactMatches("horse");

        printMatches("me");
        printExactMatches("me");
    }

    public static void printGRMatches(String gr) {
        String[] matches = findByFuzzy(gr);
        int matchesLen = matches.length;
        for (int i = 0; i < matchesLen; i++) {
            print(matches[i]);
        }
    }

    public static void printMatches(String searchTerm) {
        if (searchTerm.length() < 3) {
            System.out.println("Search term too short");
            return;
        }
        
        for (int i = 0; i < NUM_ENTRIES; i++) {
            String entry = getEntry(i);
            if (entry.contains(searchTerm)) {
                print(entry);
            }
        }
    }

    public static void printExactMatches(String searchTerm) {
        printMatches("/" + searchTerm + "/");
    }
}
