package com.shop.webbe.service.impl;


import com.shop.webbe.dto.RoleRequestDTO;
import com.shop.webbe.dto.RoleResponseDTO;
import com.shop.webbe.repository.RoleRepository;
import com.shop.webbe.repository.UserRepository;
import com.shop.webbe.service.IRoleService;
import com.shop.webcommon.entity.Role;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<RoleResponseDTO> findAll() {
        List<Role> roles = roleRepository.findAll();
        return roles.parallelStream()
                .map(role -> modelMapper.map(role, RoleResponseDTO.class))
                .collect(Collectors.toList());
    }


    @Override
    public RoleResponseDTO findById(Long id) {
        Role role = roleRepository.findById(id).orElse(null);
        return modelMapper.map(role, RoleResponseDTO.class);
    }



    @Override
    public RoleResponseDTO save(RoleRequestDTO roleDto) {
        Role role = modelMapper.map(roleDto, Role.class);
        roleRepository.save(role);
        return modelMapper.map(role, RoleResponseDTO.class);
    }


    @Override
    public void remove(Long id) {
        roleRepository.deleteById(id);
    }


}
