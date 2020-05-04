package com.example.slava.jpademo.entity.example;

import javax.persistence.Entity;

@Entity
public class ExampleChild1 extends Example{

    private String description;

    public ExampleChild1() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
