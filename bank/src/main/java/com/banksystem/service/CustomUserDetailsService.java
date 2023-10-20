package com.banksystem.service;

import com.banksystem.com.banksystem.exception.CustomerNotFoundException;
import com.banksystem.com.banksystem.repository.CustomerRepository;
import com.banksystem.model.Customer;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class CustomUserDetailsService  implements UserDetailsService {

    private CustomerRepository custRepository;
    private Logger logger = Logger.getLogger(getClass());
    public CustomUserDetailsService(CustomerRepository custRepository) {
        this.custRepository = custRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Customer customer = custRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email: "+ usernameOrEmail));

        Set<GrantedAuthority> authorities = customer
                .getRoles()
                .stream()
                .map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(customer.getEmail(),
                customer.getPassword(),
                authorities);
    }



    public Customer updateCustomer(Customer c) {
        Customer customer = custRepository.findById(c.getId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found: " + c.getId()));
        BeanUtils.copyProperties(c, customer);
        return custRepository.save(customer);
    }


    public List<Customer> getCustomers(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return custRepository.findAll(pageable).toList();
    }

      public Customer getCustomerById(int customerId) {
        Customer customer = custRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found: " + customerId));
        logger.info("Customer Found: " + customerId);
        return customer;
    }




}
