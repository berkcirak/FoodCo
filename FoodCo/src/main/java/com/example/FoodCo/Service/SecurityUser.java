package com.example.FoodCo.Service;

import com.example.FoodCo.Entity.User;
import com.example.FoodCo.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityUser implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserService userService;

    public SecurityUser(UserRepository userRepository,UserService userService){
        this.userRepository=userRepository;
        this.userService=userService;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> theUser=userRepository.findByUsername(username);
        return theUser.orElseThrow(EntityNotFoundException::new);
    }
}
