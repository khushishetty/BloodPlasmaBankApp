package com.example.bloodplasmabankapp.Models;

public class RequestBloodDonorModel {
    String name, bloodgrp, address, date, phone, email, time;

    public RequestBloodDonorModel() {
    }

    public RequestBloodDonorModel(String name, String blooggrp, String address, String date, String phone, String email, String time) {
        this.name = name;
        this.bloodgrp = blooggrp;
        this.address = address;
        this.date = date;
        this.phone = phone;
        this.email = email;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBloodgrp() {
        return bloodgrp;
    }

    public void setBloodgrp(String blooggrp) {
        this.bloodgrp = blooggrp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
