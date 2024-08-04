package com.example.FoodCo.Repository;

import com.example.FoodCo.Entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {

    Optional<Admin> findByUser_Username(String usename);
    Optional<Admin> findAdminByUserId(int id);
}
