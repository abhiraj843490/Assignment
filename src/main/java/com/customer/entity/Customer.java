package com.customer.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="customer")
public class Customer {
    private static final long serialVersionUID = 5L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(nullable = false, length = 20)
    private String first_name;
    @NotBlank
    @Column(nullable = false, length = 25)
    private String last_name;
    @NotBlank
    @Column(nullable = false,length = 30)
    private String street;
    @NotBlank
    @Column(nullable = false,length = 35)
    private String address;
    @NotBlank
    @Column(nullable = false,length = 20)
    private String city;
    @NotBlank
    private String state;
    @NotBlank
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE)
    @Column(nullable = false, unique = true)
    private String email;
    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid phone number. Please enter a 10-digit number.")
    private String phone;

}

