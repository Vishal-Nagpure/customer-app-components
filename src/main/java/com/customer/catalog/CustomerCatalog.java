package com.customer.catalog;

import com.customer.entity.Customer;

/**
 * Interface for all customer related functionality.
 *
 * @author vishal_nagpure
 */
public interface CustomerCatalog {

    boolean hasMoreCustomers();

    Customer getCustomer();
}
