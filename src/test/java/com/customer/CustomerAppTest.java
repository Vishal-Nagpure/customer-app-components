package com.customer;

import com.customer.catalog.CustomerCatalog;
import com.customer.catalog.FileSystemCustomerCatalog;
import com.customer.conf.ConfigurationManager;
import com.customer.distance.DistanceCalculatorImpl;
import com.customer.entity.Customer;
import com.customer.entity.Location;
import com.customer.exception.ConfigurationException;
import com.customer.exception.InvalidConfigKeyException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CustomerAppTest {

    private CustomerApp customerApp;
    private CustomerCatalog customerCatalog;

    @BeforeClass
    public void setup() throws FileNotFoundException, ConfigurationException, InvalidConfigKeyException {
        customerCatalog = new FileSystemCustomerCatalog(ConfigurationManager.getConfigurationManager());
        customerApp = new CustomerApp(new DistanceCalculatorImpl());
    }

    @Test
    public void should_return_customer_within_100_km() {

        List<Customer> filteredCustomers = new ArrayList<>();
        Location officeLocation = new Location(53.339428, -6.257664);

        while (customerCatalog.hasMoreCustomers()) {
            final Customer customer = customerCatalog.getCustomer();
            if (customerApp.isLocationUnderDistance(customer, 100, officeLocation))
                filteredCustomers.add(customer);
        }

        System.out.println(filteredCustomers);
        assert filteredCustomers.size() > 0;
    }

    @Test
    public void should_return_empty_list_within_0_km() {
        List<Customer> filteredCustomers = new ArrayList<>();
        Location officeLocation = new Location(53.339428, -6.257664);

        while (customerCatalog.hasMoreCustomers()) {
            final Customer customer = customerCatalog.getCustomer();
            if (customerApp.isLocationUnderDistance(customer, 0, officeLocation))
                filteredCustomers.add(customer);
        }

        System.out.println(filteredCustomers);
        assert filteredCustomers.size() == 0;
    }

    @Test
    public void should_return_empty_list_when_distance_is_negative() {
        List<Customer> filteredCustomers = new ArrayList<>();
        Location officeLocation = new Location(53.339428, -6.257664);

        while (customerCatalog.hasMoreCustomers()) {
            final Customer customer = customerCatalog.getCustomer();
            if (customerApp.isLocationUnderDistance(customer, -5, officeLocation))
                filteredCustomers.add(customer);
        }

        System.out.println(filteredCustomers);
        assert filteredCustomers.size() == 0;
    }

    @Test
    public void should_throw_exception_when_invalid_configuration_is_given() throws ConfigurationException, FileNotFoundException, InvalidConfigKeyException {
        final ConfigurationManager configurationManager = ConfigurationManager.getConfigurationManager();
        customerCatalog = new FileSystemCustomerCatalog(configurationManager);
    }
}
