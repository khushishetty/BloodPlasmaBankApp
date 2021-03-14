package com.example.bloodplasmabankapp.Models;

public class BloodDonorModel {
    String name,blood_group,city;

    public BloodDonorModel() {
    }

    public BloodDonorModel(String name, String blood_group, String city) {
        this.name = name;
        this.blood_group = blood_group;
        this.city = city;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
