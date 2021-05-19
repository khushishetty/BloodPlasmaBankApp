package com.example.bloodplasmabankapp.Models;

public class PlasmaDonorModel {
    String name,plasmagrp,city,phno;
    String email,ailments,gender,age;

    public PlasmaDonorModel() {
    }

    public PlasmaDonorModel(String name, String plasmagrp, String city, String phno, String email, String ailments, String gender, String age) {
        this.name = name;
        this.plasmagrp = plasmagrp;
        this.city = city;
        this.phno = phno;
        this.email = email;
        this.ailments = ailments;
        this.gender = gender;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getPlasmagrp() {
        return plasmagrp;
    }

    public String getCity() {
        return city;
    }

    public String getPhno() {
        return phno;
    }

    public String getEmail() {
        return email;
    }

    public String getAilments() {
        return ailments;
    }

    public String getGender() {
        return gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlasmagrp(String plasmagrp) {
        this.plasmagrp = plasmagrp;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAilments(String ailments) {
        this.ailments = ailments;
    }

    public void setGender(String address) {
        this.gender = address;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
