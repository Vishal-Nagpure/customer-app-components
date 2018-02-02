package com.customer.conf;

import com.customer.exception.ConfigurationException;
import com.customer.exception.InvalidConfigKeyException;

import java.io.InputStream;
import java.util.Map;

/**
 * Configuration manager to be used to get configuration parameters. This class
 * hides actual implementation of {@link ConfigurationReader} from rest of the
 * project. Provides API to set change implementation for {@link ConfigurationReader}
 * without impacting other classes
 *
 * @author Vishal Nagpure
 */
public class ConfigurationManager {

    private ConfigurationReader configurationReader;
    private String configFile = "configuration.properties";
    private static ConfigurationManager configManager;

    private ConfigurationManager() throws ConfigurationException {

        final InputStream inputStream = ConfigurationManager.class.getClassLoader().getResourceAsStream(configFile);
        configurationReader = new DefaultConfigurationReader();
        configurationReader.loadConfigurations(inputStream);
    }

    public static ConfigurationManager getConfigurationManager() throws ConfigurationException {
        if (null == configManager) {
            // double checked locking to ensure single object creation
            synchronized (ConfigurationManager.class) {
                if (null == configManager) {
                    configManager = new ConfigurationManager();
                }
            }
        }
        return configManager;
    }

    public String getConfig(String key) throws InvalidConfigKeyException {
        return configurationReader.getConfiguration(key);
    }

    public Map<String, String> getAllConfigurations() {
        return configurationReader.getAllConfigurations();
    }

    public void setDefaultReader(ConfigurationReader reader) {
        configurationReader = reader;
    }

    public void setDefaultReaderAndLoad(ConfigurationReader reader) throws ConfigurationException {
        configurationReader = reader;
        InputStream stream = ConfigurationManager.class.getClassLoader().getResourceAsStream(configFile);
        configurationReader.loadConfigurations(stream);
    }
}
