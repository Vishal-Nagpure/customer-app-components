package com.customer;

import com.customer.catalog.CustomerCatalog;
import com.customer.catalog.FileSystemCustomerCatalog;
import com.customer.distance.DistanceCalculatorImpl;
import com.customer.distance.Location;
import com.customer.entity.Customer;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class CustomerAppTest {

    CustomerApp customerApp;
    CustomerCatalog customerCatalog;

    @BeforeClass
    public void setup() {
        customerCatalog = new FileSystemCustomerCatalog();
        customerApp = new CustomerApp(new DistanceCalculatorImpl());
    }

    @Test
    public void should_return_customer_within_100_km() {
        final List<String> allCustomer = customerCatalog.getAllCustomer();
        Location officeLocation = new Location(53.339428, -6.257664);
        final List<Customer> filteredCustomers = customerApp.filterByDistance(allCustomer, 100, officeLocation);
        System.out.println(filteredCustomers);
        assert filteredCustomers.size() > 0;
    }

    @Test
    public void should_return_empty_list_within_0_km() {
        final List<String> allCustomer = customerCatalog.getAllCustomer();
        Location officeLocation = new Location(53.339428, -6.257664);
        final List<Customer> filteredCustomers = customerApp.filterByDistance(allCustomer, 0, officeLocation);
        System.out.println(filteredCustomers);
        assert filteredCustomers.size() == 0;
    }
}
