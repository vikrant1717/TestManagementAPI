package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    public static String configProperties(String key) throws IOException {
        File f = new File("./src/main/resources/config.properties");
        FileReader fr = new FileReader(f);
        Properties prop = new Properties();
        prop.load(fr);
        return prop.getProperty(key);
    }
}
