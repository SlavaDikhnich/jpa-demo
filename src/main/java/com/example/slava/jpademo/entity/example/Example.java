package com.example.slava.jpademo.entity.example;

import javax.persistence.*;

// @MappedSuperclass
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "EXAMPLE_TYPE")
public class Example {

    @Id
    @GeneratedValue
    private Long id;

    private String code;

    private String name;

    public Example() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
