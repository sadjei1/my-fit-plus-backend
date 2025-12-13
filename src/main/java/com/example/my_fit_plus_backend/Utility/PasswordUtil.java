package com.example.my_fit_plus_backend.Utility;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class PasswordUtil {


    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);


    public static String hashPassword(String rawPassword) {
        return encoder.encode(rawPassword);
    }
}
