package com.nexters.sororok.item;

import java.io.Serializable;

public class ContactItem implements Serializable {
    public String getUser_phNumber() {
        return user_phNumber;
    }

    public void setUser_phNumber(String user_phNumber) {
        this.user_phNumber = user_phNumber;
    }

    public String getUser_Name() {
        return user_Name;
    }

    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    public String user_phNumber, user_Name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;


}
