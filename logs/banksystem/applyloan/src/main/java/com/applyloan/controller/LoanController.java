package com.applyloan.controller;


import com.applyloan.com.applyloan.services.LoanService;
import com.applyloan.model.Loan;
import com.applyloan.model.Transaction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/loan")

public class LoanController {

   @GetMapping("/m")
    public String m(){
       System.out.println("sssssssssss");
        return "Loan application";
    }

    @Autowired(required = true)
    private LoanService loanService;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/applyLoan")
    public ResponseEntity<Loan> applyLoan(@RequestBody Loan loan) {
        return new ResponseEntity<Loan>(loanService.applyLoan(loan), HttpStatus.OK);
    }

  @GetMapping("/calculateAMI")
    public ResponseEntity<Transaction> calculateAMI(@RequestBody Transaction transaction) {
       double emi=0.0;
     int time=transaction.getDuration();
      String Durationtype=transaction.getDurationtype();
     double LoanAmt= transaction.getLoanAmt();
      double rate=transaction.getRate();
      if(Durationtype.equalsIgnoreCase("year")){
          time = time*12;		//one month period
      }
      rate = rate/(12*100);	//one month interest

      emi = (LoanAmt*rate*Math.pow(1+rate,time))/(Math.pow(1+rate,time)-1);
      transaction.setMonthlyEMI(emi);
      System.out.print("Monthly EMI is : "+emi+"\n");
      return new ResponseEntity<Transaction>(transaction, HttpStatus.OK);
    }


  /*  @GetMapping("/m1")
    public String m1(){


        return this.restTemplate.getForObject("http://localhost:9880/api/auth/m1",String.class);
    }*/
    @GetMapping("/loanbycustId/{custId}")
    public ResponseEntity<List<Loan>> findBycustId(@PathVariable int custId){

        List<Loan> loanList=loanService.findBycustId(custId);
        return new ResponseEntity<>(loanList,HttpStatus.OK);
    }

    @DeleteMapping("/foreclose/{loanId}")
    public ResponseEntity<String> forecloseLoan(@PathVariable int loanId) {
        loanService.foreCloseLoan(loanId);
        return new ResponseEntity<String>("Loan Foreclosed with Loan Id: " + loanId, HttpStatus.OK);
    }




}
