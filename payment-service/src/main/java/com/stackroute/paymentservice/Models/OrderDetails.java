package com.stackroute.paymentservice.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Document
public class OrderDetails {
    @Id
    private String _id;
    private String bookingId;
    private int amount;
    private String currencyType;
    private String orderStatus;
    private int noOfAttempts;
    private String userEmailId;
    private Date timestamp;

    public OrderDetails() {
    }

    public OrderDetails(String _id, String bookingId, int amount, String currencyType, String orderStatus, int noOfAttempts, String userEmailId, Date timestamp) {
        this._id = _id;
        this.bookingId = bookingId;
        this.amount = amount;
        this.currencyType = currencyType;
        this.orderStatus = orderStatus;
        this.noOfAttempts = noOfAttempts;
        this.userEmailId = userEmailId;
        this.timestamp = timestamp;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getNoOfAttempts() {
        return noOfAttempts;
    }

    public void setNoOfAttempts(int noOfAttempts) {
        this.noOfAttempts = noOfAttempts;
    }

    public String getUserEmailId() {
        return userEmailId;
    }

    public void setUserEmailId(String userEmailId) {
        this.userEmailId = userEmailId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
