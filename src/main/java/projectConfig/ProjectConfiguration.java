package projectConfig;

import java.io.FileInputStream;
import java.util.Properties;

public class ProjectConfiguration {

    private static final String CONFIG_FILE = System.getProperty("config");
    private static final String PROPERTIES_FILE = "src/test/resources/config/" + ((CONFIG_FILE == null) ? "default" : CONFIG_FILE) + ".properties";
    private static final Properties LOCAL_PROPS;


    static {
        LOCAL_PROPS = loadProperties();
    }


    public static Properties getProjectProperties() {
        return LOCAL_PROPS;
    }


    public static Properties loadProperties() {
        Properties result = new Properties();
        try {
            FileInputStream fileInput = new FileInputStream(PROPERTIES_FILE);
            result = new Properties();
            result.load(fileInput);
            fileInput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

