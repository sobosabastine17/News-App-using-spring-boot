package com.example.OceanNews.Modules.Roles.Controller;

import com.example.OceanNews.Modules.Roles.Roles;
import com.example.OceanNews.Modules.Roles.Service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/newsApp/v1")
public class RolesController {
    @Autowired
    RolesService rolesService;
    @PostMapping("/roles/add")
    public ResponseEntity add(@RequestBody Roles roles){
        //check if role name exists in the database
       if (!rolesService.checkRoleName(roles.getName())){ {
           return ResponseEntity.ok("Role added successfully");
       }
         }
          return ResponseEntity.badRequest().body("Role with name "+roles.getName()+" already exists");
    }
    @PatchMapping("/roles/update/{id}")
    public ResponseEntity update(@PathVariable Long id,@RequestBody Roles roles){
        if (rolesService.getRoleById(id).isPresent()){
            return ResponseEntity.ok(rolesService.updateRole(roles));
        }
        return ResponseEntity.badRequest().body("Role with id "+id+" does not exist");
    }
    @DeleteMapping("/roles/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        if (rolesService.getRoleById(id).isPresent()){
            rolesService.deleteRole(rolesService.getRoleById(id).get());
            return ResponseEntity.ok("Role with id "+id+" deleted successfully");
        }
        return ResponseEntity.badRequest().body("Role with id "+id+" does not exist");
    }
    @GetMapping("/roles/getAll")
    public Iterable<Roles> getAllRoles(){
        return rolesService.getAllRoles();
    }
}
