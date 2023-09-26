package com.customer.service;

import com.customer.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllDetails();

    Customer getDetailById(Long id);

    Customer updateDetail(Long id, Customer customer);

    void deleteStudentById(Long id);
}
