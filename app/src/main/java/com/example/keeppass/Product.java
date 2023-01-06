package com.example.keeppass;
public class Product {
    private int id;
    private String name;
    private String username;
    private String email;
    private String phno;
    private String pass_w;

    public Product(int id, String name, String username, String email, String phno,String pass_w) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.phno = phno;
        this.pass_w = pass_w;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhno() {
        return phno;
    }
    public String getPass_w() {
        return pass_w;
    }
}

