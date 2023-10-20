package com.banksystem.model;

public class SignUpDto {

    private String username;
    private String email;
    private String password;

    private String fname;
    private String lname;
    private String gender;
    private long phone;
    private String confirmPassword;
    private float salary;
    private long adhaar;
    private String pan;
    private double walletAmt;


    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }



    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public long getAdhaar() {
        return adhaar;
    }

    public void setAdhaar(long adhaar) {
        this.adhaar = adhaar;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public double getWalletAmt() {
        return walletAmt;
    }

    public void setWalletAmt(double walletAmt) {
        this.walletAmt = walletAmt;
    }
}
