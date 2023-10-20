package com.banksystem.model;

import javax.persistence.*;
import java.io.Serializable;


public class Loan  {


    private int loanId;
    private double loanAmt;
    private String loanType;
    private int duration;
    private double monthlyEMI;
    private int custId;
    private String durationtype;



    public Loan() {

    }

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public String getDurationtype() {
        return durationtype;
    }

    public void setDurationtype(String durationtype) {
        this.durationtype = durationtype;
    }

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }


    public double getLoanAmt() {
        return loanAmt;
    }

    public void setLoanAmt(double loanAmt) {
        this.loanAmt = loanAmt;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getMonthlyEMI() {
        return monthlyEMI;
    }

    public void setMonthlyEMI(double monthlyEMI) {
        this.monthlyEMI = monthlyEMI;
    }





}