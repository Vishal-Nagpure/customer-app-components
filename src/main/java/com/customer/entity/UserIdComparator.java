package com.customer.entity;

import java.util.Comparator;

public class UserIdComparator implements Comparator<Customer> {
    @Override
    public int compare(Customer o1, Customer o2) {
        return o1.getUserId() - o2.getUserId();
    }
}
