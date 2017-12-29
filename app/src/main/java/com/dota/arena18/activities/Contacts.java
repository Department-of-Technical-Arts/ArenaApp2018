package com.dota.arena18.activities;


public class Contacts {
    int image;
    String name,designation,mobile,mail;

    public Contacts(String name, String designation, String mobile, int image, String mail) {
        this.name = name;
        this.image=image;
        this.designation = designation;
        this.mobile = mobile;
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }

    public int getImage()
    {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getDesignation() {
        return designation;
    }

    public String getMobile() {
        return mobile;
    }
}

