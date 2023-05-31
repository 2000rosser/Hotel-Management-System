package service.service;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.model.Admin;
import service.repository.AdminRepository;

@Service
public class AdminService {

    @Autowired  
    AdminRepository adminRepository;

    public Admin getAdminByUsername(String username) {  
        return adminRepository.findByUsername(username);
    }
}
