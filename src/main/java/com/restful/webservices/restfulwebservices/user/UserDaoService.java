package com.restful.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDaoService {
    private static List<Person> users=new ArrayList<>();
    public static int numUsers=3;

    static {
        users.add(new Person(1,"Chirag",new Date()));
        users.add(new Person(2,"Kamal",new Date()));

    }
    public List<Person> findAll(){
        return users;
    }
    public Person save(Person user){
        if(user.getId()==null){
            user.setId(++numUsers);
        }
        users.add(user);
        return user;
    }
    public Person findOne(int id){
        for(Person user:users){
            if(user.getId()==id){
                return user;
            }
        }
        return null;
    }

    public Person deleteById(int id){
        Iterator<Person> iterator= users.iterator();
        while(iterator.hasNext()){
            Person user=iterator.next();
            if(user.getId()==id) {
                iterator.remove();
                return user;
            }
        }

        return null;
    }



}
