package com.customer;

import com.customer.distance.DistanceCalculator;
import com.customer.entity.Location;
import com.customer.entity.Customer;

/**
 * @author vishal_nagpure
 */
public class CustomerApp {

    private final DistanceCalculator distanceCalculator;

    public CustomerApp(DistanceCalculator distanceCalculator) {
        this.distanceCalculator = distanceCalculator;
    }

    public boolean isLocationUnderDistance(Customer customer, long distance, Location officeLocation) {

        return distanceCalculator.calculateDistanceInKilometer(customer.getLocation(), officeLocation) < distance;
    }
}