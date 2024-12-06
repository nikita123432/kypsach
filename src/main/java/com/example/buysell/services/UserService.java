package com.example.buysell.services;
import com.example.buysell.models.User;
import com.example.buysell.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void saveUser(User user) throws Exception {
        if(!userRepository.existsByUsername(user.getUsername())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        } else {
            throw new Exception(")");
        }
    }

    @Transactional
    public void save(User user){
        userRepository.save(user);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }
    @Transactional
    public void deleteUser(User user){
        userRepository.delete(user);
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username).get();
    }
}
