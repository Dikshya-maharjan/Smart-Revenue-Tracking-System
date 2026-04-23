package com.example.revenuemanagementsystem;

public class Transaction {

    String type;
    double amount;
    String date;
    String note;

    public Transaction(String type, double amount, String date, String note) {
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.note = note;
    }

}