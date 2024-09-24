package com.nexjot.nexjot.api.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignUpDTO {

    @NotBlank
    @Size(min=3, max=50)
    private String username;
    @NotBlank
    @Size(min=8, max=25)
    private String password;
    @NotBlank
    @Email
    private String email;
}
