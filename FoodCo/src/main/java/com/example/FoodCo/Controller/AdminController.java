package com.example.FoodCo.Controller;

import com.example.FoodCo.Entity.Admin;
import com.example.FoodCo.Entity.User;
import com.example.FoodCo.Exception.IdNotFoundException;
import com.example.FoodCo.Repository.AdminRepository;
import com.example.FoodCo.Service.AdminService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final AdminRepository adminRepository;
    public AdminController(AdminService adminService,AdminRepository adminRepository){
        this.adminService=adminService;
        this.adminRepository=adminRepository;
    }
    @PostMapping("/add")
    public Admin addAdmin(@RequestBody Admin theAdmin){
        return adminService.addAdmin(theAdmin);
    }
    @GetMapping("/list")
    public List<Admin> getAdmins(){
        return adminService.getAdmins();
    }
    @GetMapping("/list/{adminId}")
    public Admin getAdmin(@PathVariable int adminId){
        return adminService.getAdmin(adminId);
    }
    @PutMapping("/update/{adminId}")
    public Admin updateAdmin(@PathVariable int adminId, @RequestBody Admin admin) throws IdNotFoundException {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        User user=(User) authentication.getPrincipal();

        Optional<Admin> optionalAdmin=adminRepository.findAdminByUserId(user.getId());
        if (optionalAdmin.isPresent()){
            Admin theAdmin=optionalAdmin.get();
            if (theAdmin.getId()==adminId){
                admin.setId(theAdmin.getId());
                return adminService.updateAdmin(adminId, admin);
            }else{
                throw new RuntimeException("Admin found but no updated");
            }
        }else {
            throw new IdNotFoundException("Admin not found");
        }
    }
    @DeleteMapping("/delete/{adminId}")
    public void deleteAdmin(@PathVariable int adminId){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        User user=(User) authentication.getPrincipal();

        Optional<Admin> optionalAdmin=adminRepository.findAdminByUserId(user.getId());
        if (optionalAdmin.isPresent()){
            Admin admin=optionalAdmin.get();
            if (admin.getId()==adminId){
                adminRepository.deleteById(adminId);
            }else {
                throw new RuntimeException("admin found but cant deleted");
            }
        }
        else {
            throw new RuntimeException("admin is not found");
        }
    }

}

