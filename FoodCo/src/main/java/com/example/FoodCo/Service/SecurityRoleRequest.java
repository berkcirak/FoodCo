package com.example.FoodCo.Service;

import com.example.FoodCo.Entity.User;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Data
public class SecurityRoleRequest {


    public User getUserAuthorities(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        User user=(User) authentication.getPrincipal();
        return user;
    }


}
