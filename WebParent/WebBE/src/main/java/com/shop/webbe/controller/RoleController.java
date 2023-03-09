package com.shop.webbe.controller;


import com.shop.webbe.dto.RoleDto;
import com.shop.webbe.dto.RoleRequestDTO;
import com.shop.webbe.dto.RoleResponseDTO;
import com.shop.webbe.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    @Autowired
    private RoleServiceImpl roleService;

    @GetMapping
    @RolesAllowed({"ROLE_ADMIN"})
    public List<RoleResponseDTO> getAllRole() {

        return roleService.findAll();
    }

    @GetMapping("/{id}")
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<RoleResponseDTO> getRoleById(@PathVariable Long id) {
        Optional<RoleResponseDTO> role = Optional.ofNullable(roleService.findById(id));
        return role.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping("/create")
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<String> createRole(@RequestBody @Valid RoleRequestDTO roleDto) {
        roleService.save(roleDto);
        return ResponseEntity.ok("New role create successfully!");
    }




    @RolesAllowed({"ROLE_ADMIN"})
    @PutMapping()
    public ResponseEntity<RoleResponseDTO> editRole(@Valid @RequestBody RoleRequestDTO roleDto) {
        return ResponseEntity.ok(roleService.save(roleDto));
    }



    @RolesAllowed({"ROLE_ADMIN"})
    @DeleteMapping("")
    public ResponseEntity<String> deleteRole(@RequestBody RoleDto roleDto) {
        roleService.remove(roleDto.getId());
        return ResponseEntity.ok("Role deleted successfully!");
    }
}
