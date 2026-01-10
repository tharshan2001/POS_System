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
    @Autowired
    private ResponseMsg msg;


    // Create customer
    @PostMapping
    public ResponseMsg createCustomer(@RequestBody CustomerReq req) {
        Customer customer = new Customer();
        customer.setName(req.getName());
        customer.setPhone(req.getPhone());

        customerService.createCustomer(customer);

        msg.setMsg("Customer Added!");
        return msg;
    }

    // Get all customers
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    // Get single customer by phone
    @GetMapping("/phone/{phone}")
    public Customer getCustomerByPhone(@PathVariable String phone) {
        return customerService.getCustomerByPhone(phone);
    }

    // Update customer by ID
    @PutMapping("/{id}")
    public Customer updateCustomerById(@PathVariable Long id, @RequestBody Customer customer) {
        return customerService.updateCustomerById(id, customer);
    }

    // Delete customer by ID
    @DeleteMapping("/{id}")
    public ResponseMsg deleteCustomerById(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
        msg.setMsg("Customer deleted successfully");
        return msg;
    }
}