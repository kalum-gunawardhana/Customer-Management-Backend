package edu.customer_management.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerModel {
    private Long id;
    private String name;
    private String email;
    private String phone;
}