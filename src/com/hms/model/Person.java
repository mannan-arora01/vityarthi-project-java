package com.hms.model;

import java.io.Serializable;

// Implements Serializable for file persistence
public abstract class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    
    protected String id;
    protected String name;
    protected int age;

    public Person(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    
    @Override
    public String toString() {
        return String.format("ID: %s | Name: %s | Age: %d", id, name, age);
    }
}