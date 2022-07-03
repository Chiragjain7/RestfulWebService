package com.restful.webservices.restfulwebservices.user;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserService {
    @Autowired
    private UserDaoService service;

    @GetMapping("/users")
    public List<Person> retrieveAllUsers(){

        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public Person find(@PathVariable int id){
        Person founduser=service.findOne(id);
        if(founduser==null){
            throw new UserNotFoundException("id- "+id);
           // throw new RuntimeException("error");
        }
        return service.findOne(id);
    }

    @PostMapping("/users")
    public ResponseEntity createUser(@Valid @RequestBody Person user){
        Person savedUser=service.save(user);
        URI location=ServletUriComponentsBuilder.
                fromCurrentRequest().
                path("/{id}").
                buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteId(@PathVariable int id){
        Person deleteUser=service.deleteById(id);
        if(deleteUser==null){
            throw new UserNotFoundException("id- "+id);
            // throw new RuntimeException("error");
        }
        //return service.deleteById(id);
    }

}
