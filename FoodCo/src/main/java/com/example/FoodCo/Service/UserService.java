package com.example.FoodCo.Service;

import com.example.FoodCo.Entity.User;
import com.example.FoodCo.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    public Optional<User> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
