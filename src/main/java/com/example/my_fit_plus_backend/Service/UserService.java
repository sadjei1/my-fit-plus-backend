package com.example.my_fit_plus_backend.Service;

import com.example.my_fit_plus_backend.Model.User;
import com.example.my_fit_plus_backend.Repository.UserRepository;
import com.example.my_fit_plus_backend.Utility.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    /*
        User service function to find all users from the repository;
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    /*
    User service function to find a particular user.
     */
    public Optional<User> getUser(UUID id) {
        return userRepository.findById(id);
    }


    /*
    User service function to save a particular user.
     */
    public User saveUser(User user) {
        String hashedPassword = PasswordUtil.hashPassword(user.getPassword());
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }

    /*
    User service to update a user
     */
    public User updateUser(UUID id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));

        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setEmail(userDetails.getEmail());
        user.setPassword(PasswordUtil.hashPassword(userDetails.getPassword()));

        return userRepository.save(user);
    }


    /*
    User service to delete a user
     */
    public void deleteUser(UUID id) {
        User user =  userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with id: " + id +" not found."));
        userRepository.deleteById(id);
    }


    /*
    User service to get user by email
     */
    public Optional<User> getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User with email: " + email + "not found"));

        return userRepository.findByEmail(email);
    }


}
