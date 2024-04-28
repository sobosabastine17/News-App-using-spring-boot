package com.example.OceanNews.Serivices.ImpService;

import com.example.OceanNews.Model.Roles;
import com.example.OceanNews.Repo.RolesRepository;
import com.example.OceanNews.Serivices.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleImplementationService implements RolesService {
    @Autowired
    private RolesRepository rolesRepository;
    @Override
    public Roles getRoleByName(String name) {
        return rolesRepository.findRolesByName(name);
    }

    @Override
    public Optional<Roles> getRoleById(Long id) {
        //check if the id is existed
        if (!rolesRepository.existsById(id)) {
            ResponseEntity.badRequest().body("Role id is not existed");
        }
        return rolesRepository.findById(id);
    }

    @Override
    public Roles addRole(Roles role) {
        role.setName(role.getName().toUpperCase());
        role.setDescription(role.getDescription());
        return rolesRepository.save(role);
    }

    @Override
    public Roles updateRole(Roles role) {
        //check if the id is existed
        if (!rolesRepository.existsById(role.getId())) {
            ResponseEntity.badRequest().body("Role id is not existed");
        }
        role.setName(role.getName().toUpperCase());
        role.setDescription(role.getDescription());
        return rolesRepository.save(role);
    }

    @Override
    public void deleteRole(Roles role) {
        //check if the id is existed
        if (!rolesRepository.existsById(role.getId())) {
            ResponseEntity.badRequest().body("Role id is not existed");
        }
        rolesRepository.delete(role);
    }

    @Override
    public Iterable<Roles> getAllRoles() {
        return rolesRepository.findAll();
    }

    @Override
    public boolean checkRoleName(String name) {
        //check if the name is existed
        if (rolesRepository.findRolesByName(name) != null) {
            ResponseEntity.badRequest().body("Role name is already existed");
        }
        return false;
    }
}
