import java.util.Properties;
import java.io.InputStream;

public class CedictStatic {
    public static Properties entries;

    static {
        try {
            entries = new Properties();
            InputStream inputStream = CedictStatic.class.getResourceAsStream("cedict.properties");
            entries.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
