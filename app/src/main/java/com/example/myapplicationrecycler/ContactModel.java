package com.example.myapplicationrecycler;

import java.io.Serializable;

public class ContactModel implements Serializable {

    private String name;
    private String phone;
    private String image;

    public ContactModel(String name, String phone, String image) {
        this.name = name;
        this.phone = phone;
        this.image = image;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
