package com.shop.webbe.service;





import com.shop.webcommon.dto.RoleDto;

import java.util.List;

public interface IRoleService extends IGeneralService<RoleDto>{
    List<RoleDto> findAll();

    RoleDto findById(Long id);

    void save(RoleDto roleDto);

    void remove(Long id);
}
