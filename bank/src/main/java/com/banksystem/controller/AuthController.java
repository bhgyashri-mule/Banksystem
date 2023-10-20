package com.banksystem.controller;


import com.banksystem.com.banksystem.repository.CustomerRepository;
import com.banksystem.com.banksystem.repository.RoleRepository;
import com.banksystem.model.*;

import com.banksystem.service.CustomUserDetailsService;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private RestTemplate restTemplate;


    private Logger logger = Logger.getLogger(getClass());



    @GetMapping("/m1")
    public String m1(){
        return "hi,,,,,";
    }
    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto){
       // System.out.println("cccccc"+loginDto.getUsernameOrEmail());
        logger.info("cccccc"+loginDto.getUsernameOrEmail());
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){


        // add check for username exists in a DB
        if(customerRepository.existsByUsername(signUpDto.getUsername())){

            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        // add check for email exists in DB
        if(customerRepository.existsByEmail(signUpDto.getEmail())){
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }

        // create user object
        Customer user = new Customer();
        user.setFname(signUpDto.getFname());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setLname(signUpDto.getLname());
        user.setGender(signUpDto.getGender());
        user.setPhone(signUpDto.getPhone());
        user.setSalary(signUpDto.getSalary());

       // user.setAdhaar((signUpDto.getAdhaar());
        user.setPan(signUpDto.getPan());
        user.setWalletAmt(signUpDto.getWalletAmt());

        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        user.setConfirmPassword(passwordEncoder.encode(signUpDto.getConfirmPassword()));
        //System.out.println("cccccc"+signUpDto.getEmail());
        logger.info("cccccc"+signUpDto.getEmail());
        Role roles = roleRepository.findByName("ROLE_ADMIN").get();
        System.out.println("cccccc222"+roles);
        user.setRoles(Collections.singleton(roles));




        customerRepository.save(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

    }


    // Updating Customer

    @PutMapping
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer c) {
        return new ResponseEntity<Customer>(customUserDetailsService.updateCustomer(c), HttpStatus.OK);
    }

    // Fetching all Customers

    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers(@RequestParam("page") int pageNumber,
                                                       @RequestParam("size") int pageSize) {
        return new ResponseEntity<List<Customer>>(customUserDetailsService.getCustomers(pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("/customerbycustId/{custId}")
    public ResponseEntity<Customer> findBycustId(@PathVariable int custId){
        Customer cust=customUserDetailsService.getCustomerById(custId);
        List<Loan> loanList=null;
        loanList= this.restTemplate.getForObject("http://applyloan-service/loan/loanbycustId/"+custId,List.class);
        cust.setLoanList(loanList);
        return new ResponseEntity<Customer>(cust,HttpStatus.OK);
    }

}
