package com.example.betapp.user_model;

import java.util.Comparator;

public class UsersComparator implements Comparator<User> {

    @Override
    public int compare(User o1, User o2) {
        return Float.compare(o2.getUserPoints(), o1.getUserPoints());
    }
}

