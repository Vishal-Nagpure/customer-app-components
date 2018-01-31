package com.customer.distance;

public class DistanceCalculatorImpl implements DistanceCalculator {

    public static final int AVERAGE_RADIUS_OF_EARTH_KM = 6371;

    @Override
    public int calculateDistanceInKilometer(Location customerLocation, Location officeLocation) {

        final double latitudeDistance = Math.toRadians(customerLocation.getLatitude() - officeLocation.getLatitude());
        final double longitudeDistance = Math.toRadians(customerLocation.getLongitude() - officeLocation.getLongitude());
        final double sinLatitude = Math.sin(latitudeDistance / 2);
        final double sinLongitude = Math.sin(longitudeDistance / 2);

        double a = sinLatitude * sinLatitude +
                (Math.cos(Math.toRadians(customerLocation.getLatitude()))
                        * Math.cos(Math.toRadians(officeLocation.getLatitude()))
                        * sinLongitude * sinLongitude);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return (int) (AVERAGE_RADIUS_OF_EARTH_KM * c);
    }
}
