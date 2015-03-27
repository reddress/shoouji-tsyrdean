import java.util.Properties;
import java.io.InputStream;

public class CedictInstance {
    public Properties entries;

    public CedictInstance() {
        System.out.println("initialize cedict");
        try {
            entries = new Properties();
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("cedict.properties");
            entries.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
