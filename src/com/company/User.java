package com.company;

import java.util.ArrayList;

/**
 * Created by michaelmernin on 12/6/16.
 */
public class User {
    String name;
    String password;

    ArrayList<Messages> messages = new ArrayList<>();

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }


}
