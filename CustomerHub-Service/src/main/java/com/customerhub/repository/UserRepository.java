package com.customerhub.repository;

import com.customerhub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    public Optional<User> findByEmail(String email);
//    Optional<User> findByUserId(String userId);
    public Optional<User> findByUsername(String username);
}
