import static mandarin.Conversion.*;
import static mandarin.Cedict.*;

public class CLI {

    // javac -encoding "UTF-8" MyFrame.java
    
    public static <T> void print(T val) {
        System.out.println(val);
    }

    public static void main(String[] args) {
        /*
        print(py2gr("zu2"));
        print("盤");
        print(Cedict.entries.size());
        */

        final int NUM_ENTRIES = Cedict.entries.size();
        for (int i = 0; i < NUM_ENTRIES; i++) {
            String entry = getEntry(i);
            if (entry.contains("/horse/")) {
                print(entry);
            }
        }
    }
}
