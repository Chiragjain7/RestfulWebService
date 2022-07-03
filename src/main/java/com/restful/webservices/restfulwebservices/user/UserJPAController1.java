package com.restful.webservices.restfulwebservices.user;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJPAController1 {
    @Autowired
    private UserDaoService service;

    @Autowired
    private UserRepositoryService userRepositoryService;

    @Autowired
    private PostRepositoryService postRepositoryService;

    @GetMapping("jpa/users")
    public List<Person> retrieveAllUsers(){

        return userRepositoryService.findAll();
    }

    @GetMapping("jpa/users/{id}")
    public Optional<Person> find(@PathVariable int id){
        Optional<Person> foundUser=userRepositoryService.findById(id);
        if(!foundUser.isPresent()){
            throw new UserNotFoundException("id- "+id);
            // throw new RuntimeException("error");
        }
        return userRepositoryService.findById(id);
    }

    @PostMapping("jpa/users")
    public ResponseEntity createUser(@Valid @RequestBody Person user){
        Person savedUser=userRepositoryService.save(user);
        URI location=ServletUriComponentsBuilder.
                fromCurrentRequest().
                path("/{id}").
                buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("jpa/users/{id}")
    public void updatePerson(@RequestBody Person person, @PathVariable int id){
        Optional<Person> foundUser=userRepositoryService.findById(id);
        if(!foundUser.isPresent()){
            throw new UserNotFoundException("id- "+id);
            // throw new RuntimeException("error");
        }
        //System.out.println(person.getDate());
        Person per=foundUser.get();
        //System.out.println(per);
        per.setDate(person.getDate());
        userRepositoryService.save(per);
    }

    @DeleteMapping("jpa/users/{id}")
    public void deleteId(@PathVariable int id){
        userRepositoryService.deleteById(id);
    }

    @GetMapping("jpa/users/{id}/posts")
    public List<Post> getPosts(@PathVariable int id){
        Optional<Person> person=userRepositoryService.findById(id);
        if(!person.isPresent()){
            throw new UserNotFoundException("id- "+id);
        }
        return person.get().getPost();

    }

    @GetMapping("jpa/users/{id}/posts/{pid}")
    public Optional<Post> getPostsById(@PathVariable int pid){
        Optional<Post> post=postRepositoryService.findById(pid);
        if(!post.isPresent()){
            throw new UserNotFoundException("post id- "+pid);
        }
        return postRepositoryService.findById(pid);

    }


    @PostMapping("jpa/users/{id}/posts")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ResponseEntity createPost(@PathVariable int id,@RequestBody Post post){
        Optional<Person> person=userRepositoryService.findById(id);
        if(!person.isPresent()){
            throw new UserNotFoundException("id- "+id);
        }
        Person person1=person.get();
        post.setPerson(person1);
        Post savedPost=postRepositoryService.save(post);
        URI location=ServletUriComponentsBuilder.
                fromCurrentRequest().
                path("/{id}").
                buildAndExpand(savedPost.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("jpa/users/{id}/posts/{pid}")
    public void updatePosts(@RequestBody Post post, @PathVariable int pid){
        Optional<Post> foundPost=postRepositoryService.findById(pid);
        if(!foundPost.isPresent()){
            throw new UserNotFoundException("post id- "+pid);
        }
        postRepositoryService.findById(pid);
        //System.out.println(person.getDate());
        Post updatedPost=foundPost.get();
        //System.out.println(per);
        updatedPost.setDescription(post.getDescription());
        postRepositoryService.save(updatedPost);
    }

    @DeleteMapping("jpa/users/{id}/posts/{pid}")
    public void deletePostId(@PathVariable int pid){
        postRepositoryService.deleteById(pid);
    }


}
