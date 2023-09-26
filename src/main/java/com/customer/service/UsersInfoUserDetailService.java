package com.customer.service;

import com.customer.entity.UserEntity;
import com.customer.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
@Configuration
public class UsersInfoUserDetailService implements UserDetailsService {
    @Autowired
    UserRepo userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity = userRepository.findByEmail(email);
        return userEntity.map(UsersInfoDetails::new)
                .orElseThrow(()->new UsernameNotFoundException("user not found "+email));

    }
}
