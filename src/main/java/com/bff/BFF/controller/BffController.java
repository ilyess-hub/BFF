package com.bff.BFF.controller;




import com.bff.BFF.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@RestController
@RequestMapping("/bff")
public class BffController {

    private final WebClient.Builder webClientBuilder;



    @GetMapping("/{id}")
    public Mono<User> getUserById(String id) {
        // Build a WebClient instance using the webClientBuilder
        WebClient webClient = webClientBuilder.build();

        // Use the WebClient instance to call the user microservice endpoint that returns a user with the specified id
        return webClient.get()
                .uri("http://my-spring-boot-app:9090/user/{id}", id)
                .retrieve()
                .bodyToMono(User.class);
    }


    @GetMapping
    public Flux<User> getAllUsers() {
        // Build a WebClient instance using the webClientBuilder
        WebClient webClient = webClientBuilder.build();

        // Use the WebClient instance to call the user microservice endpoint that returns all users
        return webClient.get()
                .uri("http://my-spring-boot-app:9090/user")
                .retrieve()
                .bodyToFlux(User.class);
    }




    @PostMapping
    public Mono<User> createUser(User user) {
        // Build a WebClient instance using the webClientBuilder
        WebClient webClient = webClientBuilder.build();

        // Use the WebClient instance to call the user microservice endpoint that creates a new user
        return webClient.post()
                .uri("http://my-spring-boot-app:9090/user")
                .bodyValue(user)
                .retrieve()
                .bodyToMono(User.class);
    }









    @PutMapping("/{id}")
    public Mono<User> updateUser(String id, User user) {
        // Build a WebClient instance using the webClientBuilder
        WebClient webClient = webClientBuilder.build();

        // Use the WebClient instance to call the user microservice endpoint that updates an existing user with the specified id
        return webClient.put()
                .uri("http://my-spring-boot-app:9090/user/{id}", id)
                .bodyValue(user)
                .retrieve()
                .bodyToMono(User.class);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteUser(String id) {
        // Build a WebClient instance using the webClientBuilder
        WebClient webClient = webClientBuilder.build();

        // Use the WebClient instance to call the user microservice endpoint that deletes an existing user with the specified id
        return webClient.delete()
                .uri("http://my-spring-boot-app:9090/user/{id}", id)
                .retrieve()
                .bodyToMono(Void.class);
    }



}

