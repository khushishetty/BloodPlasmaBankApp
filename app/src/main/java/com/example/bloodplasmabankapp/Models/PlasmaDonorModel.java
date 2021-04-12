package com.example.bloodplasmabankapp.Models;

public class PlasmaDonorModel {
    String name,plasmagrp,city;

    public PlasmaDonorModel() {
    }

    public PlasmaDonorModel(String name, String plasmagrp, String city) {
        this.name = name;
        this.plasmagrp = plasmagrp;
        this.city = city;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setPlasmagrp(String plasmagrp) {
        this.plasmagrp = plasmagrp;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
