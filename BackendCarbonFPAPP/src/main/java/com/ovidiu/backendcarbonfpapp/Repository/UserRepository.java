package com.ovidiu.backendcarbonfpapp.Repository;

import com.ovidiu.backendcarbonfpapp.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}