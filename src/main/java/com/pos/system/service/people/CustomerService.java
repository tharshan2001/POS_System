package com.pos.system.service.people;

import com.pos.system.entity.people.Customer;

import java.util.List;

public interface CustomerService {

    void createCustomer(Customer customer);

    Customer getCustomerById(Long id);

    Customer getCustomerByPhone(String phone);

    List<Customer> getAllCustomers();

    Customer updateCustomerById(Long id, Customer customer);

    void deleteCustomerById(Long id);
}