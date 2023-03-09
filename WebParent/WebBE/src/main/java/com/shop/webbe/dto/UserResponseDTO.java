package com.shop.webbe.dto;

import com.shop.webcommon.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String fullname;

    private String username;

    private String password;

    private String email;

    private String photo;

    private Boolean status;

    private List<Role> roles;

}
