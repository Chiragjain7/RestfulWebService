package com.restful.webservices.restfulwebservices.versioning;

public class Person2 {
    private Name name;

    public Person2(){

    }
    public Person2(Name name) {
        this.name = name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Name getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Person2{" +
                "name=" + name +
                '}';
    }
}
