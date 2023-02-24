package com.bff.BFF.services;

import com.bff.BFF.models.User;
import com.bff.BFF.repos.UserRepo;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Data
public class UserService {



    @Value("${user.service.host}")
    private String userServiceHost;

    @Value("${user.service.port}")
    private int userServicePort;

    @Value("${user.service.path}")
    private String userServicePath;

    private final WebClient.Builder webClientBuilder;




    public Mono<User> getUserById(String id) {

        // Build a WebClient instance using the webClientBuilder
        WebClient webClient = webClientBuilder.build();

        // Use the WebClient instance to call the user microservice endpoint that returns a user with the specified id
        return webClient.get()
                .uri(uri -> uri.
                        host(userServiceHost).
                        port(userServicePort).
                        path(userServicePath+"/"+id)
                        .build()
                )
                .retrieve()
                .bodyToMono(User.class);
    }

    public Flux<User> getAllUsers() {

        // Build a WebClient instance using the webClientBuilder
        WebClient webClient = webClientBuilder.build();

        // Use the WebClient instance to call the user microservice endpoint that returns all users
        return webClient.get()
                .uri(uri -> uri.
                        host(userServiceHost).
                        port(userServicePort).
                        path(userServicePath)
                        .build()
                )
                .retrieve()
                .bodyToFlux(User.class);


    }



    public Mono<User> createUser(User user) {

        // Build a WebClient instance using the webClientBuilder
        WebClient webClient = webClientBuilder.build();

        // Use the WebClient instance to call the user microservice endpoint that creates a new user
        return webClient.post()
                .uri(uri -> uri.
                        host(userServiceHost).
                        port(userServicePort).
                        path(userServicePath)
                        .build()
                )
                .bodyValue(user)
                .retrieve()
                .bodyToMono(User.class);


    }









    public Mono<User> updateUser(String id, User user) {

        // Build a WebClient instance using the webClientBuilder
        WebClient webClient = webClientBuilder.build();

        // Use the WebClient instance to call the user microservice endpoint that updates an existing user with the specified id
        return webClient.put()
                .uri(uri -> uri.
                        host(userServiceHost).
                        port(userServicePort).
                        path(userServicePath+"/"+id)
                        .build()
                )
                .bodyValue(user)
                .retrieve()
                .bodyToMono(User.class);


    }

    public Mono<Void> deleteUser(String id) {

        // Build a WebClient instance using the webClientBuilder
        WebClient webClient = webClientBuilder.build();

        // Use the WebClient instance to call the user microservice endpoint that deletes an existing user with the specified id
        return webClient.delete()
                .uri(uri -> uri.
                        host(userServiceHost).
                        port(userServicePort).
                        path(userServicePath+"/"+id)
                        .build()
                )
                .retrieve()
                .bodyToMono(Void.class);
    }
    }


