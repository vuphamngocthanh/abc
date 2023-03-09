 package com.shop.webbe.dto;



import com.shop.webbe.validator.EqualPasswordConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualPasswordConstraint(message = "Please confirm password again! ")
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

