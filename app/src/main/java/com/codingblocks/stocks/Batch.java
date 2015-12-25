package com.codingblocks.stocks;

import java.util.ArrayList;

/**
 * Created by nagarro on 06/09/15.
 */
public class Batch {
    String name;
    String courseName;
    int capacity;
    int currentlyFilled;
    boolean isOpen;
    ArrayList<Student> students;

    public Batch(String name, String courseName, int currentlyFilled, int capacity, boolean isOpen) {
        this.name = name;
        this.capacity = capacity;
        this.courseName = courseName;
        this.currentlyFilled = currentlyFilled;
        this.isOpen = isOpen;
    }

}
