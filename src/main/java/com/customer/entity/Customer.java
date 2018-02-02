package com.customer.entity;

public class Customer implements Comparable<Customer> {

    private int userId;
    private String userName;
    private Location location;

    public Customer() {
    }

    public Customer(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        return userId == customer.userId;
    }

    @Override
    public int hashCode() {
        return userId;
    }

    @Override
    public String toString() {
        return userId + " " + userName;
    }

    @Override
    public int compareTo(Customer obj) {
        return this.getUserId() - obj.getUserId();
    }
}
