package com.customer.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity(name = "cus_address")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CusAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long address_id;
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
}
