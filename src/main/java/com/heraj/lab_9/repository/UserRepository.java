package com.heraj.lab_9.repository;

import com.heraj.lab_9.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);

    List<User> findByUsernameAndPassword(String username, String password);
}
