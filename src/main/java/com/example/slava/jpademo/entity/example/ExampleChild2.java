package com.example.slava.jpademo.entity.example;

import javax.persistence.Entity;

@Entity
public class ExampleChild2 extends Example {

    private String catalog;

    public ExampleChild2() {
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }
}
