package com.example.bloodplasmabankapp.Models;

public class BloodDonorModel {
    String name,blood_group,city;

    String phno,mail;

    public BloodDonorModel() {
    }

    public BloodDonorModel(String name, String blood_group, String city,String p,String m) {
        this.name = name;
        this.blood_group = blood_group;
        this.city = city;
        this.phno=p;
        this.mail=m;
    }

    public String getName() {
        return name;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public String getCity() {
        return city;
    }

    public String getPhno() {
        return phno;
    }

    public String getMail() {
        return mail;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
