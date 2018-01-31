package com.customer;

import com.customer.distance.DistanceCalculator;
import com.customer.distance.Location;
import com.customer.entity.Customer;
import com.customer.entity.UserIdComparator;
import com.customer.util.Constants;

import java.util.*;

public class CustomerApp {

    DistanceCalculator distanceCalculator;

    public CustomerApp(DistanceCalculator distanceCalculator) {
        this.distanceCalculator = distanceCalculator;
    }

    public List<Customer> filterByDistance(List<String> customers, long distance, Location officeLocation) {

        List<Customer> customerList = new ArrayList<>();

        for (String customer : customers) {
            final Map<String, Object> data = getCustomerMap(customer.substring(1, customer.length() - 1));
            final int currentDistance = distanceCalculator.calculateDistanceInKilometer(
                    new Location(
                            Double.valueOf(data.get(Constants.LATITUDE).toString()),
                            Double.valueOf(data.get(Constants.LONGITUDE).toString())), officeLocation);

            if (currentDistance < distance) {
                customerList.add(new Customer(Integer.parseInt((String) data.get(Constants.USER_ID)), (String) data.get(Constants.USER_NAME)));
            }

        }

        Collections.sort(customerList, new UserIdComparator());

        return customerList;
    }

    private Map<String, Object> getCustomerMap(String customer) {
        final Map<String, Object> map = new HashMap<>();
        for (String tuple : customer.split(",")) {
            final String[] split = tuple.split(":");
            map.put(split[0].replace("\"", "").trim(), split[1].replace("\"", "").trim());
        }

        return map;
    }
}