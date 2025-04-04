package edu.customer_management.service;

import edu.customer_management.entity.CustomerEntity;
import edu.customer_management.model.CustomerModel;
import java.util.List;
import java.util.Optional;

public interface CustomerService {
    CustomerModel toModel(CustomerEntity customer);
    CustomerEntity toEntity(CustomerModel model);
    CustomerModel createCustomer(CustomerModel customer);
    List<CustomerModel> getAllCustomers();
    Optional<CustomerModel> getCustomerById(Long id);
    Optional<CustomerModel> updateCustomer(Long id, CustomerModel updatedCustomer);
    void deleteCustomer(Long id);
    List<CustomerModel> searchCustomersByName(String name);
}
