package edu.customer_management.controller;

import edu.customer_management.model.CustomerModel;
import edu.customer_management.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
@CrossOrigin
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    // Create a new customer
    @PostMapping("/create-customer")
    public ResponseEntity<CustomerModel> createCustomer(@RequestBody CustomerModel customer) {
        CustomerModel createdCustomer = customerService.createCustomer(customer);
        return ResponseEntity.ok(createdCustomer);
    }

    // Get all customers
    @GetMapping("get-customers")
    public ResponseEntity<List<CustomerModel>> getAllCustomers() {
        List<CustomerModel> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    // Get a customer by ID
    @GetMapping("/{id}")
    public ResponseEntity<CustomerModel> getCustomerById(@PathVariable Long id) {
        Optional<CustomerModel> customer = customerService.getCustomerById(id);
        return customer.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update a customer
    @PutMapping("/{id}")
    public ResponseEntity<CustomerModel> updateCustomer(@PathVariable Long id, @RequestBody CustomerModel updatedCustomer) {
        Optional<CustomerModel> updated = customerService.updateCustomer(id, updatedCustomer);
        return updated.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete a customer
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    // Search customers by name
    @GetMapping("/search-customer-name")
    public ResponseEntity<List<CustomerModel>> searchCustomersByName(@RequestParam String name) {
        List<CustomerModel> result = customerService.searchCustomersByName(name);
        return ResponseEntity.ok(result);
    }
}
