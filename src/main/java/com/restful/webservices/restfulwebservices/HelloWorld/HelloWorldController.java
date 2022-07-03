package com.restful.webservices.restfulwebservices.HelloWorld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
public class HelloWorldController {

    @Autowired
    private ResourceBundleMessageSource messageSource;

    //Get Mapping
    @GetMapping("/hello-world")
    public String HelloWorld(){
        return "Hello World1";
    }

    @GetMapping("/hello-world-bean")
    public HelloWorld1 HelloWorldBean(){

        return new HelloWorld1("Hello World");
    }

    @GetMapping("/hello-world-bean/{name}")
    public HelloWorld1 HelloWorldPathVariable(@PathVariable String name){
        return new HelloWorld1(String.format("Hello World, %s",name));
    }

    @GetMapping("/hello-world-internationalized")
    public String HelloWorldInternationalized(@RequestHeader(name = "Accept-Language",required = false) Locale locale){
        return messageSource.getMessage("good.morning.message",null,locale);
//        return new HelloWorld1(String.format("Hello World, %s",name));
    }


}
