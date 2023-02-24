package com.bff.BFF.services;

import com.bff.BFF.models.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Data
@Slf4j
public class UserService {



    @Value("${user.service.host}")
    private String userServiceHost;

    @Value("${user.service.port}")
    private int userServicePort;

    @Value("${user.service.path}")
    private String userServicePath;

    @Value("${user.service.userServiceBaseUrl}")
    private String userServiceBaseUrl;

    private final WebClient.Builder webClientBuilder;


    public Mono<User> getUserById(String id) {

        // Build a WebClient instance using the webClientBuilder
        WebClient webClient = webClientBuilder.build();
        log.info("webClientBuilder");
        // Use the WebClient instance to call the user microservice endpoint that returns a user with the specified id
        return webClient.get()
                .uri(uri -> uri
                        .scheme("http")
                        .host(userServiceHost)
                        .port(userServicePort)
                        .path(userServicePath+"/"+id)
                        .build()
                )
                .retrieve()
                .bodyToMono(User.class)
                .doOnSuccess(x -> System.out.println(x.getId()));


    }

    public Flux<User> getAllUsers() {

        // Build a WebClient instance using the webClientBuilder
        WebClient webClient = webClientBuilder.build();

        // Use the WebClient instance to call the user microservice endpoint that returns all users
        return webClient.get()
                .uri(uri -> uri
                        .scheme("http")
                        .host(userServiceHost).
                        port(userServicePort).
                        path(userServicePath)
                        .build()
                )
                .retrieve()
                .bodyToFlux(User.class);


    }

    public <T> Mono<T> createOrUpdateResource(T obj ,Class<T> response, String resourcePath, HttpMethod httpMethod) {
        WebClient webClient = WebClient.builder()
                .baseUrl(userServiceBaseUrl) // Replace with your base URL
                .build();

        return webClient.method(httpMethod)
                .uri(resourcePath)
                .body(BodyInserters.fromValue(obj))
                .retrieve()
                .bodyToMono(response);
    }

    public Mono<User> createUser(User user) {
        String resourcePath = "/user/"; // Replace with your endpoint path
        return createOrUpdateResource(user, User.class, resourcePath, HttpMethod.POST);
    }

    public Mono<User> updateUser(User user) {
        String resourcePath = "/users/" + user.getId(); // Replace with your endpoint path
        return createOrUpdateResource(user, User.class, resourcePath, HttpMethod.PUT);
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


