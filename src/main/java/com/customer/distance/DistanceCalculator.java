package com.customer.distance;

import com.customer.entity.Location;

public interface DistanceCalculator {

    int calculateDistanceInKilometer(Location customerLocation, Location officeLocation);
}
