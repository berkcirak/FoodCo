package com.example.FoodCo.Service;

import com.example.FoodCo.Entity.Admin;
import com.example.FoodCo.Entity.Role;
import com.example.FoodCo.Entity.User;
import com.example.FoodCo.Exception.IdNotFoundException;
import com.example.FoodCo.Repository.AdminRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private AdminRepository adminRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AdminService(AdminRepository adminRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.adminRepository=adminRepository;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
    }
    public Admin addAdmin(Admin admin){
        User user=User.builder()
                .username(admin.getUser().getUsername())
                .password(bCryptPasswordEncoder.encode(admin.getUser().getPassword()))
                .authorities(Collections.singleton(Role.ROLE_ADMIN))
                .accountNonExpired(true)
                .accountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .build();

        Admin theAdmin=Admin.builder()
                .user(user)
                .build();
        return adminRepository.save(theAdmin);
    }
    public List<Admin> getAdmins(){
        return adminRepository.findAll();
    }
    public Admin getAdmin(int adminId){
        return adminRepository.findById(adminId).orElseThrow();
    }
    public Admin updateAdmin(int adminId, Admin admin) throws IdNotFoundException {
        Optional<Admin> optionalAdmin=adminRepository.findById(adminId);
        if (optionalAdmin.isPresent()){
            Admin theAdmin=optionalAdmin.get();

            User toUser=theAdmin.getUser();
            User fromUser=admin.getUser();
            if (toUser!=null && fromUser!=null){
                toUser.setUsername(fromUser.getUsername());
                toUser.setPassword(fromUser.getPassword());
                toUser.setAccountNonLocked(fromUser.isAccountNonLocked());
                toUser.setAccountNonExpired(fromUser.isAccountNonExpired());
                toUser.setEnabled(fromUser.isEnabled());
                toUser.setCredentialsNonExpired(fromUser.isCredentialsNonExpired());
                toUser.setAuthorities(fromUser.getAuthorities());
                theAdmin.setUser(toUser);
            }
            adminRepository.save(theAdmin);
            return theAdmin;
        }
        else {
            throw new IdNotFoundException("Id not found");
        }
    }
    public void deleteAdmin(int adminId){
        adminRepository.deleteById(adminId);
    }

}
