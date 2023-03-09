package com.shop.webbe.service;



import com.shop.webbe.dto.RoleRequestDTO;
import com.shop.webbe.dto.RoleResponseDTO;

import java.util.List;

public interface IRoleService {
    List<RoleResponseDTO> findAll();

    RoleResponseDTO findById(Long id);

    RoleResponseDTO save(RoleRequestDTO roleRequestDTO);

    void remove(Long id);
}
