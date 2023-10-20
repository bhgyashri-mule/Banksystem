package com.banksystem.model;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "customer" ,uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Size(min=2, message="Username should have atleast 2 characters")
    private String username;
    @Email
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
    @Transient
private List<Loan> loanList=new ArrayList<>();

    public Customer(int id, String username, String email, String password, String fname, String lname, String gender, long phone, String confirmPassword, float salary, long adhaar, String pan, double walletAmt, List<Loan> loanList, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
        this.phone = phone;
        this.confirmPassword = confirmPassword;
        this.salary = salary;
        this.adhaar = adhaar;
        this.pan = pan;
        this.walletAmt = walletAmt;
        this.loanList = loanList;
        this.roles = roles;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

    public Customer(String username,String firstName, String lastName, String email, String password,String confirmPassword,String gender,long phone,float salary,
                    long adhaar, String pan,double walletAmt) {
        this.username=username;
        this.fname = firstName;
        this.lname = lastName;
        this.email = email;
        this.password = password;
        this.confirmPassword=confirmPassword;
        this.gender=gender;
        this.phone=phone;
        this.pan=pan;
        this.adhaar=adhaar;
        this.salary=salary;
        this.walletAmt=walletAmt;

    }

    public Customer() {
    }

    public String getUsername() {
        return username;
    }

    public List<Loan> getLoanList() {
        return loanList;
    }

    public void setLoanList(List<Loan> loanList) {
        this.loanList = loanList;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public void setAdhaar(long adhaar) {
        this.adhaar = adhaar;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public void setWalletAmt(double walletAmt) {
        this.walletAmt = walletAmt;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getGender() {
        return gender;
    }

    public long getPhone() {
        return phone;
    }

    public float getSalary() {
        return salary;
    }

    public long getAdhaar() {
        return adhaar;
    }

    public String getPan() {
        return pan;
    }

    public double getWalletAmt() {
        return walletAmt;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return  fname;
    }

    public String getLastName() {
        return lname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.lname = name;
    }

    public void setLastName(String lastName) {
        this.lname = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
