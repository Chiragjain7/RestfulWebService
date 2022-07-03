package com.restful.webservices.restfulwebservices.versioning;

public class Person1 {
    private String name;
    public Person1(){

    }
    public Person1(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person1{" +
                "name='" + name + '\'' +
                '}';
    }
}
