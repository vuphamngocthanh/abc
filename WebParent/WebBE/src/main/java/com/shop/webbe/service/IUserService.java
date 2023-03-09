package com.shop.webbe.service;



import com.shop.webbe.dto.UserRequestDTO;
import com.shop.webbe.dto.UserResponseDTO;

import java.util.List;

public interface IUserService {
    List<UserResponseDTO> findAll();

    UserResponseDTO findById(Long id);

    UserResponseDTO save(UserRequestDTO userCreateDTO);

    List<UserResponseDTO> findAllByNameContaining(String name);

    void remove(Long id);
}
