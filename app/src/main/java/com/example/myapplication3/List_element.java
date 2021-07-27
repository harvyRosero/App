package com.example.myapplication3;

import android.widget.ImageButton;

import java.io.Serializable;

public class List_element implements Serializable {

    public String name;
    public String city;
    public String status;

    //lista de elementos
    public List_element(String name, String city, String status) {

        this.name = name;
        this.city = city;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    //fin lista de elementos
}
