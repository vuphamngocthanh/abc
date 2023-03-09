package com.shop.webbe.service.impl;


import com.shop.webbe.dto.UserRequestDTO;
import com.shop.webbe.dto.UserResponseDTO;
import com.shop.webbe.payload.request.AuthRequest;
import com.shop.webbe.repository.RoleRepository;
import com.shop.webbe.repository.UserRepository;
import com.shop.webbe.service.IUserService;
import com.shop.webcommon.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    AuthenticationManager authManager;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<UserResponseDTO> findAll() {
        List<User> users = userRepository.findAll();
        return users.parallelStream()
                .map(user -> modelMapper.map(user, UserResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO findById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return modelMapper.map(user, UserResponseDTO.class);
    }

    @Override
    public UserResponseDTO save(UserRequestDTO userCreateDTO) {
        User user = modelMapper.map(userCreateDTO, User.class);
        if (!userCreateDTO.getPassword().isEmpty()) {
            String hashedPassword = BCrypt.hashpw(userCreateDTO.getPassword(), BCrypt.gensalt(10));
            user.setPassword(hashedPassword);
        }
        if (userCreateDTO.getId()!=null) {
            userRepository.save(user);
        } else {
            for (User u: userRepository.findAll()) {
                if(u.getUsername().equals(userCreateDTO.getUsername())){
                    return null;
                }
            }
        }
        userRepository.save(user);
        return modelMapper.map(user,UserResponseDTO.class);
    }

    @Override
    public List<UserResponseDTO> findAllByNameContaining(String search) {
        List<User> entities = userRepository.findAllByNameContaining(search);
        List<UserResponseDTO> users = new ArrayList<>(
                entities.stream()
                        .parallel()
                        .map(entity -> modelMapper.map(entity, UserResponseDTO.class))
                        .collect(Collectors.toList()));
        return users;
    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    public UserRequestDTO getPrincipalUser(AuthRequest authRequest){
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(), authRequest.getPassword())
        );
        User user = (User) authentication.getPrincipal();
        return modelMapper.map(user,UserRequestDTO.class);
    }
}
