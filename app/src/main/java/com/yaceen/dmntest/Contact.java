package com.yaceen.dmntest;

public class Contact {
    private int id;

    private String name;
    private String phone;
    public Contact(int id ,String name,String phone){
        this.id = id;
        this.name = name;
        this.phone = phone;
    };

    public Contact(String name,String phone){
        this.name = name;
        this.phone = phone;
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return id + ") " +name +" : "+ phone;
    }
}
