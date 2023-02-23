package com.bff.BFF.repos;

import com.bff.BFF.models.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserRepo extends ReactiveMongoRepository<User,String> {

}
