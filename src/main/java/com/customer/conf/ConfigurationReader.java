package com.customer.conf;

import com.customer.exception.ConfigurationException;
import com.customer.exception.InvalidConfigKeyException;

import java.io.InputStream;
import java.util.Map;

/**
 * Interface for all configuration related APIs.
 *
 * @author Vishal Nagpure
 */
public interface ConfigurationReader {

    void loadConfigurations(InputStream inputStream) throws ConfigurationException;

    void reloadConfigurations() throws ConfigurationException;

    String getConfiguration(String key) throws InvalidConfigKeyException;

    Map<String, String> getAllConfigurations();
}
