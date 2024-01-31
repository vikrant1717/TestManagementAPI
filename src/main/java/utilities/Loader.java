package utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Loader {

    public static String configPropertiesLoader(String key) throws IOException {
        // Load config.properties using ClassLoader
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream("config.properties");

        if (input == null) {
            throw new IOException("Sorry, unable to find config.properties");
        }

        // Load the properties file
        Properties prop = new Properties();
        prop.load(input);

        // Don't forget to close the InputStream
        try {
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prop.getProperty(key);
    }
}
