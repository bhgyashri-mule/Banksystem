package com.applyloan.dao;

import com.applyloan.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository  extends JpaRepository<Loan,Integer> {

   /* @Query("select l from Loan l where l.id=?1")
    Customer findByCustomerId(int custId);*/

   List<Loan> findBycustId(int custId);

}
