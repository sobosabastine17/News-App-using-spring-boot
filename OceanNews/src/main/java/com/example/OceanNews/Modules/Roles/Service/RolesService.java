package com.example.OceanNews.Modules.Roles.Service;

import com.example.OceanNews.Modules.Roles.Roles;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface RolesService {
    Roles getRoleByName(String name);
    Optional<Roles> getRoleById(Long id);
    Roles addRole(Roles role);
    Roles updateRole(Roles role);
    void deleteRole(Roles role);
    Iterable<Roles> getAllRoles();
    boolean checkRoleName(String name);

}
