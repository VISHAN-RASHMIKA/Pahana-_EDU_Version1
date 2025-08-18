package com.pahana.model;

import java.util.Date;

public class Bill {
    private int billId;
    private String accountNumber;
    private double totalAmount;
    private Date billDate;

    public Bill(int billId, String accountNumber, double totalAmount, Date billDate) {
        this.billId = billId;
        this.accountNumber = accountNumber;
        this.totalAmount = totalAmount;
        this.billDate = billDate;
    }

    // Getters and Setters
    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }
}