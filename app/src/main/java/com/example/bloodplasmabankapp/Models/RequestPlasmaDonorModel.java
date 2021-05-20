package com.example.bloodplasmabankapp.Models;

public class RequestPlasmaDonorModel {
    String name, bloodgrp, address, date, phone, email, time,type, urgent,age;


    public RequestPlasmaDonorModel() {
    }

    public RequestPlasmaDonorModel(String name, String bloodgrp, String address, String date, String phone, String email, String time,String urgent,String age) {
        this.name = name;
        this.bloodgrp = bloodgrp;
        this.address = address;
        this.date = date;
        this.phone = phone;
        this.email = email;
        this.time = time;
        this.type = "plasma";
        this.urgent = urgent;
        this.age = age;
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

    public void setBloodgrp(String bloodgrp) {
        this.bloodgrp = bloodgrp;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrgent() {
        return urgent;
    }

    public void setUrgent(String urgent) {
        this.urgent = urgent;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
