package com.manajero.disciplinedAgileDelivery.repository;

import com.manajero.disciplinedAgileDelivery.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {
    Optional<User> findUserByEmail(String email);
}
