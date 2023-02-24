package com.bff.BFF.controller;




import com.bff.BFF.models.User;
import com.bff.BFF.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;



    @PostMapping
    public Mono<User> createUser(User user) {
        return userService.createUser(user);
    }




    @GetMapping("/{id}")
    public Mono<User> getUserById(String id) {
        return userService.getUserById(id);
    }

    @GetMapping
    public Flux<User> getAllUsers() {
        return userService.getAllUsers();
    }







    @PutMapping("/{id}")
    public Mono<User> updateUser(String id, User user) {
       return userService.updateUser(id,user);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteUser(String id) {
        return userService.deleteUser(id);


    }
}

