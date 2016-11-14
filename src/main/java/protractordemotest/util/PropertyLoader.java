package protractordemotest.util;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {

    private static final String APPLICATION_PROPERTIES = "/application.properties";

    public static Capabilities loadCapabilities() throws IOException {
        Properties props = new Properties();
        props.load(PropertyLoader.class.getResourceAsStream(System.getProperty("application.properties", APPLICATION_PROPERTIES)));
        String capabilitiesFile = props.getProperty("capabilities");

        Properties capsProps = new Properties();
        capsProps.load(PropertyLoader.class.getResourceAsStream(capabilitiesFile));

        DesiredCapabilities capabilities = new DesiredCapabilities();
        for (String name : capsProps.stringPropertyNames()) {
            String value = capsProps.getProperty(name);
            if (value.toLowerCase().equals("true") || value.toLowerCase().equals("false")) {
                capabilities.setCapability(name, Boolean.valueOf(value));
            } else if (value.startsWith("file:")) {
                capabilities.setCapability(name, new File(".", value.substring(5)).getCanonicalFile().getAbsolutePath());
            } else {
                capabilities.setCapability(name, value);
            }
        }

        return capabilities;
    }

    public static String loadProperty(String name) throws IOException {
        Properties properties = new Properties();
        properties.load(PropertyLoader.class.getResourceAsStream(
                System.getProperty("application.properties", APPLICATION_PROPERTIES)));
        return properties.getProperty(name);
    }
}