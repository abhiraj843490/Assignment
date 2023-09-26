package com.customer.service;

import com.customer.entity.Customer;
import com.customer.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class serviceImpl implements CustomerService {
    @Autowired
    private CustomerRepo customerRepository;

    @Override
    public List<Customer> getAllDetails() {
        List<Customer> customer = customerRepository.findAll();
        return customer;
    }

    @Override
    public Customer getDetailById(Long id) {
        Customer customer = customerRepository.findById(id).get();
        return customer;
    }

    @Override
    public Customer updateDetail(Long id, Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void deleteStudentById(Long id) {
//        Customer customer = customerRepository.findById(id).get();
        customerRepository.deleteById(id);
    }
}
