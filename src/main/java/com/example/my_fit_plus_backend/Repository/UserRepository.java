package com.example.my_fit_plus_backend.Repository;

import com.example.my_fit_plus_backend.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
