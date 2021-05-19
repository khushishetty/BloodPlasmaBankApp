package com.example.bloodplasmabankapp.Models;

public class BloodDonorModel {
    String name,blood_group,city;

    String phno,mail;
    String ailments,gender,age;

    public BloodDonorModel() {
    }

    public BloodDonorModel(String name, String blood_group, String city,String p,String m,String ail,String gender,String age) {
        this.name = name;
        this.blood_group = blood_group;
        this.city = city;
        this.phno=p;
        this.mail=m;
        this.ailments = ail;
        this.gender = gender;
        this.age = age;
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

    public String getAilments() {
        return ailments;
    }

    public String getGender() {
        return gender;
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

    public void setAilments(String ailments) {
        this.ailments = ailments;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
