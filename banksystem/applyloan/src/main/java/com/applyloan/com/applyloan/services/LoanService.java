package com.applyloan.com.applyloan.services;

import com.applyloan.dao.LoanRepository;
import com.applyloan.exception.LoanNotFoundException;
import com.applyloan.model.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService  {

    @Autowired
    private LoanRepository loanDao;

    // @Autowired
    // private CustomerRepository customerDao;

    private Logger logger = Logger.getLogger(getClass());

    public Loan applyLoan(Loan loan) {
        logger.info("Loan Type...."+loan.getLoanType());
        logger.info("Duration...."+loan.getDuration());
        //customer.addLoan(loan);
        return loanDao.save(loan);
    }
    public List<Loan> findBycustId(int custId){
        return loanDao.findBycustId(custId);
    }
    ///here call the customer microservices
   /* public List<Loan> getLoansByCustomerId(int customerId) {
        Customer customer = customerDao.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found: " + customerId));
        return customer.getLoans();
    }*/


    public void foreCloseLoan(int loanId) {
        Loan loan = loanDao.findById(loanId).orElseThrow(() -> new LoanNotFoundException("Loan Not Found: " + loanId));
        loanDao.delete(loan);

    }
}