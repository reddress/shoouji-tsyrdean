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
        print(getMatches("dna"));
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

    public static String getMatches(String searchTerm) {
		String entry;
		StringBuilder result = new StringBuilder();
		
		if (searchTerm.length() < 3) {
			return "Search term must be at least 3 letters long";
		}
		
		for (int i = 0; i < NUM_ENTRIES; i++) {
			entry = getEntry(i);
			if (entry.contains(searchTerm)) {
				result.append(entry);
				result.append("\n");
			}
		}
		return result.toString();
    }

    public static void printExactMatches(String searchTerm) {
        printMatches("/" + searchTerm + "/");
    }
}
