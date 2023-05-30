package service;

import models.Customer;


import java.util.Collection;
import java.util.HashSet;


public class CustomerService {
    private static final CustomerService reference= new CustomerService();
    private final Collection<Customer> collection;
    private CustomerService(){
         this.collection = new HashSet<>();
     }
     public static CustomerService getInstance(){
         return reference;
     }

    public void addCustomer(String email, String firstName, String lastName) {
        Customer customer = new Customer(firstName, lastName, email);
        collection.add(customer);

    }

    public Customer getCustomer(String customerEmail) {
        for (Customer customer : collection) {
            if (customer.getEmail().equals(customerEmail)) {
                return customer;
            }
        }
        return null;
    }

    public Collection<Customer> getAllCustomers() {
        return collection;
    }

}