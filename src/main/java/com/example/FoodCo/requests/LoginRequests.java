package com.example.FoodCo.requests;

import lombok.Data;

@Data
public class LoginRequests {

    private String username;
    private String password;
    private String authorities;
}
