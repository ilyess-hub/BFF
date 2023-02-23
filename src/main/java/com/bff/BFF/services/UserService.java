package com.bff.BFF.services;

import com.bff.BFF.models.User;
import com.bff.BFF.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    public Mono<User> getUserById(String id) {
        return userRepo.findById(id);
    }

    public Flux<User> getAllUsers() {
        return userRepo.findAll();
    }

    public Mono<User> createUser(User user) {
        return userRepo.save(user);
    }

    public Mono<User> updateUser(String id, User user) {
        return userRepo.findById(id)
                .flatMap(existingUser -> {
                    existingUser.setName(user.getName());
                    existingUser.setBalance(user.getBalance());
                    return userRepo.save(existingUser);
                });
    }

    public Mono<Void> deleteUser(String id) {
        return userRepo.deleteById(id);
    }
}

