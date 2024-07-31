package com.example.FoodCo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;
@Entity
@
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String password;

    private boolean accountNonExpired;
    private boolean isCredentialsNonExpired;
    private boolean accountNonLocked;
    private boolean isEnabled;

    private Set<Role>


}
