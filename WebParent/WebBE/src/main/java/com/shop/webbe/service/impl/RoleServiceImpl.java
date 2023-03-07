package com.shop.webbe.service.impl;



import com.shop.webbe.repository.RoleRepository;
import com.shop.webbe.repository.UserRepository;
import com.shop.webbe.service.IRoleService;
import com.shop.webcommon.dto.RoleDto;
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
    public List<RoleDto> findAll() {
        List<Role> roles = roleRepository.findAll();
        return roles.parallelStream()
                .map(role -> modelMapper.map(role, RoleDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public RoleDto findById(Long id) {
        Role role = roleRepository.findById(id).orElse(null);
        return modelMapper.map(role, RoleDto.class);
    }

    @Override
    public void save(RoleDto roleDto) {
        Role role = modelMapper.map(roleDto, Role.class);
        roleRepository.save(role);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void remove(Long id) {
        roleRepository.deleteById(id);
    }


}
