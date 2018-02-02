package com.customer.client;

import com.customer.CustomerHandler;
import com.customer.entity.Customer;
import com.customer.exception.ConfigurationException;
import com.customer.exception.InvalidConfigKeyException;

import java.io.FileNotFoundException;
import java.util.Set;

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

        final int distance = Integer.parseInt(args[0]);
        final CustomerHandler customerHandler = new CustomerHandler();
        final Set<Customer> customerList = customerHandler.getCustomersWithinDistance(distance);

        System.out.println("There are " + customerList.size() + " customers within distance " + distance);
        System.out.println(customerList);
    }

}
