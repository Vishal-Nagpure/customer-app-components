package com.customer;

import com.customer.catalog.CustomerCatalog;
import com.customer.catalog.FileSystemCustomerCatalog;
import com.customer.conf.ConfigurationManager;
import com.customer.distance.DistanceCalculatorImpl;
import com.customer.entity.Customer;
import com.customer.entity.Location;
import com.customer.exception.ConfigurationException;
import com.customer.exception.InvalidConfigKeyException;
import com.customer.util.Constants;

import java.io.FileNotFoundException;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Vishal Nagpure
 */
public class CustomerHandler {

    public Set<Customer> getCustomersWithinDistance(int distance) throws FileNotFoundException, InvalidConfigKeyException, ConfigurationException {

        CustomerApp customerApp = new CustomerApp(new DistanceCalculatorImpl());
        final ConfigurationManager configurationManager = ConfigurationManager.getConfigurationManager();
        CustomerCatalog customerCatalog = new FileSystemCustomerCatalog(configurationManager);
        Location officeLocation = new Location(Double.parseDouble(configurationManager.getConfig(Constants.OFFICE_LATITUDE)),
                Double.parseDouble(configurationManager.getConfig(Constants.OFFICE_LONGITUDE)));

        final Set<Customer> customerList = new TreeSet<>();

        while (customerCatalog.hasMoreCustomers()) {
            Customer customer = customerCatalog.getCustomer();
            if (customerApp.isLocationUnderDistance(customer, distance, officeLocation))
                customerList.add(customer);
        }
        return customerList;
    }
}
