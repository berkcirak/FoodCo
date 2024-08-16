package com.example.FoodCo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;
@Entity
@Table(name = "tbl_users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    @JoinTable(name = "authorities",joinColumns = @JoinColumn(name = "user_id"))
    @ElementCollection(targetClass = Role.class,fetch = FetchType.EAGER)
    private Set<Role> authorities;


}
