package com.pos.system.impl.people;

import com.pos.system.entity.people.Customer;
import com.pos.system.exception.ResourceNotFoundException;
import com.pos.system.repository.people.CustomerRepository;
import com.pos.system.service.people.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public void createCustomer(Customer customer) {
        try {
            if (customerRepository.existsByPhone(customer.getPhone())) {
                throw new RuntimeException("Customer with this phone already exists");
            }
            customerRepository.save(customer);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Customer with this phone already exists");
        }
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
    }

    @Override
    public Customer getCustomerByPhone(String phone) {
        return customerRepository.findByPhone(phone)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with phone: " + phone));
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer updateCustomerById(Long id, Customer customerData) {
        Customer customer = getCustomerById(id);

        customer.setName(customerData.getName());
        customer.setEmail(customerData.getEmail());
        customer.setAddress(customerData.getAddress());
        customer.setCreditLimit(customerData.getCreditLimit());
        customer.setCreditDays(customerData.getCreditDays());
        // ❗ Do NOT update phone

        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.delete(getCustomerById(id));
    }
}