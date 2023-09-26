package com.customer.controller;

import com.customer.entity.Customer;
import com.customer.repository.CustomerRepo;
import com.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;


@RequestMapping("/customer")
@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    CustomerRepo customerRepository;

    private static final Logger log = Logger.getLogger(String.valueOf(CustomerController.class));

    @PostMapping(value = "/register")
    public ResponseEntity<?> postStudentDetail(@RequestBody Customer customer) {
        Customer saved = customerRepository.save(customer);
        return ResponseEntity.ok(saved);
    }


    @GetMapping("/all")
    public ResponseEntity<?> getAllDetails() {
        List<Customer> list = customerService.getAllDetails();
        log.info("get all details of students");
        return ResponseEntity.ok(list);
    }


    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getDetailById(@PathVariable(value = "id") Long id) {
        Customer returnValue = customerService.getDetailById(id);
        log.info("get all details of students");
        return ResponseEntity.ok(returnValue);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateDetail(@PathVariable Long id,
                                          @RequestBody Customer customer) {
        log.info("get detail by enrollment number "+id );
        Customer existingCus = customerService.getDetailById(id);
        if(existingCus==null){
            throw new UsernameNotFoundException("customer not found with this is id: "+id);
        }
        existingCus.setFirst_name(customer.getFirst_name());
        existingCus.setLast_name(customer.getLast_name());
        existingCus.setStreet(customer.getStreet());
        existingCus.setCity(customer.getCity());
        existingCus.setEmail(customer.getEmail());
        existingCus.setPhone(customer.getPhone());
        existingCus.setAddress(customer.getAddress());
        existingCus.setState(customer.getState());
        Customer updateDetail = customerService.updateDetail(id, existingCus);
        return ResponseEntity.ok(updateDetail);
    }


    @DeleteMapping("/del/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(value = "id") Long id) {
        customerService.deleteStudentById(id);

        return ResponseEntity.ok(id + " id deleted successfully!");
    }


}
