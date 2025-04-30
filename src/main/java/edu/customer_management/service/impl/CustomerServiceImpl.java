package edu.customer_management.service.impl;

import edu.customer_management.entity.CustomerEntity;
import edu.customer_management.model.CustomerModel;
import edu.customer_management.repository.CustomerRepository;
import edu.customer_management.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ModelMapper modelMapper;

    // Convert Entity to DTO
    public CustomerModel toModel(CustomerEntity customer) {
        return modelMapper.map(customer, CustomerModel.class);
    }

    // Convert DTO to Entity
    public CustomerEntity toEntity(CustomerModel model) {
        return modelMapper.map(model, CustomerEntity.class);
    }

    // Create a new customer
    public CustomerModel createCustomer(CustomerModel customer) {
        CustomerEntity customerEntity = toEntity(customer);
        CustomerEntity saveCustomerEntity = customerRepository.save(customerEntity);
        return toModel(saveCustomerEntity);
    }

    // Get all customers
    public List<CustomerModel> getAllCustomers() {
        List<CustomerEntity> allCustomerEntityList = customerRepository.findAll();
        return allCustomerEntityList.stream().map(this::toModel).collect(Collectors.toList());
    }

    // Get a customer by ID
    public Optional<CustomerModel> getCustomerById(Long id) {
        return customerRepository.findById(id).map(this::toModel);
    }

    // Update a customer
    public Optional<CustomerModel> updateCustomer(Long id, CustomerModel updatedCustomer) {
        return customerRepository.findById(id).map(existing -> {
            existing.setName(updatedCustomer.getName());
            existing.setEmail(updatedCustomer.getEmail());
            existing.setPhone(updatedCustomer.getPhone());
            CustomerEntity saved = customerRepository.save(existing);
            return toModel(saved);
        });
    }

    // Delete a customer
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    // Search customers by name (optional)
    public List<CustomerModel> searchCustomersByName(String name) {
        List<CustomerEntity> byNameContaining = customerRepository.findByNameContaining(name);
        return byNameContaining.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
