package com.shop.webbe.service.impl;


;
import com.shop.webbe.payload.request.AuthRequest;
import com.shop.webbe.repository.UserRepository;
import com.shop.webbe.service.IUserService;
import com.shop.webcommon.dto.UserRequestDTO;
import com.shop.webcommon.dto.UserResponseDTO;
import com.shop.webcommon.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    AuthenticationManager authManager;
    @Autowired
    private UserRepository userRepository;
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
        return modelMapper.map(userRepository.save(user), UserResponseDTO.class);
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
