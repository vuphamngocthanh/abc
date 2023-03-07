package com.shop.webcommon.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private Long id;
    @NotNull(message="Name is not empty")
    private String username;

    @NotNull(message = "Email not empty")
    @Pattern(regexp = "^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})", message = "Email invalid")
    private String email;

    @NotNull (message = "Email not empty")
    @Min(value = 8, message = "Password phải từ 8 kí tự trở lên")
    @Pattern(regexp = "^[a-zA-Z0-9._-]{8,40}$" , message = "Password phải từ 8 kí tự trở lên")
    private String password;
    @NotEmpty(message = "Password not empty")
    private String confirmPassword;
    private boolean status;

    private String avatar;
    private String createdTime;


}

