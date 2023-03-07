package com.shop.webbe.service;





import com.shop.webcommon.dto.UserRequestDTO;
import com.shop.webcommon.dto.UserResponseDTO;

import java.util.List;

public interface IUserService {
    List<UserResponseDTO> findAll();

    UserResponseDTO findById(Long id);

    UserResponseDTO save(UserRequestDTO userCreateDTO);

    void remove(Long id);
}
