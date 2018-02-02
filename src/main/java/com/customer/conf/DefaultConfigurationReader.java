package com.customer.conf;

import com.customer.exception.ConfigurationException;
import com.customer.exception.InvalidConfigKeyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Default implementation provided to read configuration parameters set by the user.
 * Expect the configuration to be of form key=value
 *
 * @author Vishal Nagpure
 */
public class DefaultConfigurationReader implements ConfigurationReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultConfigurationReader.class);
    private final Map<String, String> propertyMap = new HashMap<>();
    private InputStream inputStream = null;


    DefaultConfigurationReader() {
        // Limit instantiation of this class to classes within same package
    }

    /**
     * Reads and loads all the properties in memory from specified configuration
     * file. Call to this method is not thread safe.
     */
    @Override
    public void loadConfigurations(InputStream inputStream) throws ConfigurationException {

        this.inputStream = inputStream;

        try {
            Properties properties = new Properties();
            properties.load(inputStream);
            storePropertiesInMemory(properties);
        } catch (IOException e) {
            LOGGER.error("Exception while reading data from file ", e);;
        }
    }

    private void storePropertiesInMemory(Properties properties) {

        final Enumeration<?> propertyNames = properties.propertyNames();

        while (propertyNames.hasMoreElements()) {
            String key = (String) propertyNames.nextElement();
            String value = (String) properties.get(key);
            propertyMap.put(key, value);
        }
    }

    @Override
    public void reloadConfigurations() throws ConfigurationException {

        propertyMap.clear();
        loadConfigurations(inputStream);
    }

    @Override
    public String getConfiguration(String key) throws InvalidConfigKeyException {

        if (null == key)
            return null;
        if (propertyMap.containsKey(key))
            return propertyMap.get(key);

        throw new InvalidConfigKeyException("Invalid key [" + key + "]");
    }

    @Override
    public Map<String, String> getAllConfigurations() {

        return Collections.unmodifiableMap(propertyMap);
    }
}
