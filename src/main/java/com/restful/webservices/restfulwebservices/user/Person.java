package com.restful.webservices.restfulwebservices.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.List;

@Entity
public class Person {
    @Id
    @GeneratedValue
    private Integer id;

    @Size(min = 2)
    private String name;

    @Past
    private Date date;

    @OneToMany(mappedBy = "person",cascade = CascadeType.ALL)
    private List<Post> post;

    public Person(){

    }

    Person(Integer id, String name, Date date){
        this.id=id;
        this.name=name;
        this.date=date;

    }
    public void setId(Integer id){
        this.id=id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public List<Post> getPost() {
        return post;
    }

    public void setPost(List<Post> post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                '}';
    }
}
