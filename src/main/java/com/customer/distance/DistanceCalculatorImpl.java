package com.customer.distance;

import com.customer.entity.Location;

public class DistanceCalculatorImpl implements DistanceCalculator {

    private static final int AVERAGE_RADIUS_OF_EARTH_KM = 6371;

    /*
        https://en.wikipedia.org/wiki/Great-circle_distance
        Delta sigma = arccos(sin(theta1) * sin(theta2) + cos(theta1) * cos(theta2) * cos(Delta gamma)).
     */
    @Override
    public int calculateDistanceInKilometer(Location customerLocation, Location officeLocation) {

        final double sinLatitudeCustomer = Math.sin(Math.toRadians(customerLocation.getLatitude()));
        final double sinLatitudeOffice = Math.sin(Math.toRadians(officeLocation.getLatitude()));
        final double cosinLatitudeCustomer = Math.cos(Math.toRadians(customerLocation.getLatitude()));
        final double cosinLatitudeOffice = Math.cos(Math.toRadians(officeLocation.getLatitude()));

        final double cosinlongitudeDelta = Math.cos(Math.abs(Math.toRadians(customerLocation.getLongitude()) - Math.toRadians(officeLocation.getLongitude())));

        final double centralAngel = Math.acos((sinLatitudeCustomer * sinLatitudeOffice) + (cosinLatitudeCustomer * cosinLatitudeOffice * cosinlongitudeDelta));

        return (int) (AVERAGE_RADIUS_OF_EARTH_KM * centralAngel);
    }
}
