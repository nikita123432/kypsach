package com.example.buysell.services;

import com.example.buysell.models.Role;
import com.example.buysell.models.User;
import com.example.buysell.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor

public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean createUser(User user) {
        String userEmail = user.getEmail();
        if (userRepository.findByEmail(userEmail) != null) return false;
        user.setActive(true);
        user.getRole().add(Role.ROLE_USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("Saving new User with email: {}", userEmail);
        userRepository.save(user);
        return true;
    }


//    public void save(User user){
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        userRepository.save(user);
//    }
}
