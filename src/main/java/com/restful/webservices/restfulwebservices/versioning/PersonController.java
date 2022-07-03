package com.restful.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @GetMapping("v1/person")
    public Person1 getDetails(){
        return new Person1("Chirag Jain");
    }

    @GetMapping("v2/person")
    public Person2 getDetails2(){
        return new Person2(new Name("Chirag","Jain"));
    }

    @GetMapping(value = "person/params",params = "version=1")
    public Person1 getDetailsReqParam1(){
        return new Person1("Chirag Jain");
    }

    @GetMapping(value = "person/params",params = "version=2")
    public Person2 getDetailsReqParam2(){
        return new Person2(new Name("Chirag","Jain"));
    }


}
