package com.yaceen.dmntest;

import java.util.ArrayList;

public class DataBase {
    public static ArrayList<Contact> contactList = new ArrayList<>();

    public static void initData() {
        if (contactList.isEmpty()) {
            contactList.add(new Contact("Ali", "0612345678"));
            contactList.add(new Contact("Sara", "0623456789"));
            contactList.add(new Contact("Omar", "0634567890"));
        }
    }


}
