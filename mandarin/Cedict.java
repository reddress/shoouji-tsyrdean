package mandarin;

import java.util.Properties;
import java.io.InputStreamReader;
import java.io.FileInputStream;

public class Cedict {
    public static Properties entries;

    static {
        try {
            entries = new Properties();
            // InputStream inputStream = Cedict.class.getResourceAsStream("cedict.properties");
            // entries.load(inputStream);
            entries.load(new InputStreamReader(new FileInputStream("cedict.properties"), "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getEntry(int index) {
        return entries.getProperty(String.valueOf(index));
    }
}
