package com.example.my_fit_plus_backend.Repository;
import java.util.UUID;

import com.example.my_fit_plus_backend.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

}
