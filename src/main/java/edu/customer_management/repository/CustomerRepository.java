package edu.customer_management.repository;

import edu.customer_management.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    List<CustomerEntity> findByName(String name);

    List<CustomerEntity> findByNameContaining(String name);
}