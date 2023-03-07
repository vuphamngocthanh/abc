package com.shop.webbe.controller;



import com.shop.webbe.service.impl.RoleServiceImpl;
import com.shop.webcommon.dto.RoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;


@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    @Autowired
    private RoleServiceImpl roleService;

    @GetMapping
    @RolesAllowed({"ROLE_ADMIN"})
    public List<RoleDto> getAllRole(){
        List<RoleDto> roleDtoList = (List<RoleDto>) roleService.findAll();
    return roleDtoList;
    }

    @GetMapping("/{id}")
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<RoleDto> getRoleById(@PathVariable Long id){
        RoleDto role = roleService.findById(id);
        if(role == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(role);
    }


    @PostMapping("/create")
    @RolesAllowed({"ROLE_ADMIN"})
    public  ResponseEntity<Object> createRole(@RequestBody RoleDto roleDto){
        roleService.save(roleDto);
        return ResponseEntity.ok(roleDto);
    }

    @GetMapping("/edit/{id}")
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<RoleDto> showEditForm(@PathVariable Long id){
        RoleDto roleDto = roleService.findById(id);
        return ResponseEntity.ok(roleDto);
    }

    @PostMapping("/edit")
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<Object> editRole(@RequestBody RoleDto roleDto){
        roleService.save(roleDto);
        return ResponseEntity.ok(roleDto);
    }

    @GetMapping("/delete/{id}")
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<RoleDto> showDeleteForm(@PathVariable Long id){
        RoleDto roleDto = roleService.findById(id);
        return ResponseEntity.ok(roleDto);
    }

    @PostMapping("/delete")
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity deleteRole (@RequestBody RoleDto roleDto){
        roleService.remove(roleDto.getId());
        return ResponseEntity.ok("Role deleted successfully!");
    }
}
