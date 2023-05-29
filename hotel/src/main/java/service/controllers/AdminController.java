package service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import service.model.Admin;
import service.service.AdminService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/admin/check")
    public Admin checkAdmin(@RequestBody Admin admin) {
        System.out.println("Received a request to check admin credentials");
        Admin dbAdmin = adminService.getAdminByUsername(admin.getUsername());
        System.out.println("dbAdmin: " + dbAdmin);
        if (dbAdmin != null && dbAdmin.getPassword().equals(admin.getPassword())) {
            return dbAdmin;
        }
        //return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        
        return null;
    }
}
