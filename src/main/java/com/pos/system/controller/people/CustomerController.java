package com.pos.system.controller.people;

import com.pos.system.dto.people.CustomerReq;
import com.pos.system.dto.user.ResponseMsg;
import com.pos.system.entity.people.Customer;
import com.pos.system.service.people.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor


public class CustomerController {

    private final CustomerService customerService;

    // Create customer
    @PostMapping
    public ResponseMsg createCustomer(@RequestBody CustomerReq req) {
        Customer customer = new Customer();
        customer.setName(req.getName());
        customer.setPhone(req.getPhone());

        customerService.createCustomer(customer);

        // Create a new ResponseMsg object
        ResponseMsg msg = new ResponseMsg();
        msg.setMsg("Customer Added!");
        return msg;
    }

    // Get all customers
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    // Get single customer
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    // Update customer
    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        return customerService.updateCustomer(id, customer);
    }

    // Delete customer
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }
}