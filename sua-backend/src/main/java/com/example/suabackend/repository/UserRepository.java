package com.example.suabackend.repository;


import com.example.suabackend.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository using for access user entity
 */
public interface UserRepository extends CrudRepository<User, String> {
    User getUserByUsername(String username);
}
