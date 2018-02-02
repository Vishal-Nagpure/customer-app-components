package com.customer.catalog;

import com.customer.conf.ConfigurationManager;
import com.customer.entity.Customer;
import com.customer.entity.Location;
import com.customer.exception.InvalidConfigKeyException;
import com.customer.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * File based implementation of customer catalog.
 *
 * @author vishal_nagpure
 */
public class FileSystemCustomerCatalog implements CustomerCatalog {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileSystemCustomerCatalog.class);
    private String fileName;
    private BufferedReader br;
    private Customer customer;
    private boolean isFirstCustomer = true;

    public FileSystemCustomerCatalog(ConfigurationManager configurationManager) throws FileNotFoundException, InvalidConfigKeyException {
        this.fileName = configurationManager.getConfig(Constants.CUSTOMER_DATA_SOURCE_FILE);
        br = new BufferedReader(new FileReader(fileName));
        resolveCustomer();
    }

    private void resolveCustomer() {

        try {
            String line = br.readLine();
            if (line == null) {
                customer = null;
                return;
            }
            // remove curly braces
            line = line.substring(1, line.length() - 1);

            customer = new Customer();

            for (String tuple : line.split(",")) {
                resolveField(customer, tuple);
            }
        } catch (IOException e) {
            LOGGER.error("Exception while reading data from file ", e);
        }
    }

    private void resolveField(Customer customer, String tuple) {

        final String[] tupleArray = tuple.split(":");

        final String fieldName = tupleArray[0].replace("\"", "").trim();
        final String fieldValue = tupleArray[1].replace("\"", "").trim();
        if (fieldName.equalsIgnoreCase(Constants.NAME))
            customer.setUserName(fieldValue);
        else if (fieldName.equalsIgnoreCase(Constants.USER_ID))
            customer.setUserId(Integer.parseInt(fieldValue));
        else
            resolveLocation(customer, fieldName, fieldValue);
    }

    private void resolveLocation(Customer customer, String fieldName, String fieldValue) {

        if (customer.getLocation() == null)
            customer.setLocation(new Location());

        if (Constants.LATITUDE.equals(fieldName))
            customer.getLocation().setLatitude(Double.valueOf(fieldValue));

        if (Constants.LONGITUDE.equals(fieldName))
            customer.getLocation().setLongitude(Double.valueOf(fieldValue));
    }

    @Override
    public boolean hasMoreCustomers() {

        return customer != null;
    }

    @Override
    public Customer getCustomer() {
        final Customer toReturn = clone(customer);
        resolveCustomer();
        return toReturn;
    }

    private Customer clone(final Customer customer) {
        Customer cloned = new Customer();
        cloned.setUserId(customer.getUserId());
        cloned.setLocation(customer.getLocation());
        cloned.setUserName(customer.getUserName());
        return cloned;
    }
}
