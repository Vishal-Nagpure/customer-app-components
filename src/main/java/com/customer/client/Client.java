package com.customer.client;

import com.customer.CustomerApp;
import com.customer.catalog.CustomerCatalog;
import com.customer.catalog.FileSystemCustomerCatalog;
import com.customer.conf.ConfigurationManager;
import com.customer.distance.DistanceCalculatorImpl;
import com.customer.entity.Customer;
import com.customer.entity.Location;
import com.customer.exception.ConfigurationException;
import com.customer.exception.InvalidConfigKeyException;

import java.io.FileNotFoundException;
import java.util.Set;
import java.util.TreeSet;

/**
 * Starting point of application
 *
 * @author vishal_nagpure
 */
public class Client {

    public static void main(String[] args) throws FileNotFoundException, ConfigurationException, InvalidConfigKeyException {

        if (null == args) {
            System.out.println("Invalid Input!");
            System.exit(0);
        }

        CustomerApp customerApp = new CustomerApp(new DistanceCalculatorImpl());
        CustomerCatalog customerCatalog = new FileSystemCustomerCatalog(ConfigurationManager.getConfigurationManager());
        Location officeLocation = new Location(53.339428, -6.257664);
        final Set<Customer> customerList = new TreeSet<>();

        while (customerCatalog.hasMoreCustomers()) {
            Customer customer = customerCatalog.getCustomer();
            if (customerApp.isLocationUnderDistance(customer, Integer.parseInt(args[0]), officeLocation))
                customerList.add(customer);
        }

        System.out.println(customerList);
        System.out.println(customerList.size());
    }
}
