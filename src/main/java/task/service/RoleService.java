package task.service;

import task.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task.repository.RoleRepository;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleRepository repository;

    public List<Role> readAll() {
        return repository.findAll();
    }

    public Role findRoleByID(String id) {
        return repository.findById(id).orElse(null);
    }

    public Role saveNewRole(Role role) {
        return repository.save(role);
    }

    public boolean delete(String id) {
        if(repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }

    public Role updateRole(String id, Role role) {
            repository.deleteById(id);
            return repository.save(role);
    }
}
