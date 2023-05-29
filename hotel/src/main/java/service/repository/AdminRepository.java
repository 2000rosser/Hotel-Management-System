package service.repository;  

import org.springframework.data.repository.CrudRepository;
import service.model.Admin;

public interface AdminRepository extends CrudRepository<Admin, Integer> {
    Admin findByUsername(String username);
}
