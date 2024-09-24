package com.nexjot.nexjot.api.dto.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginDTO {
    @NotNull
    @Size(min=3, max=50)
    private String username;
    @Size(min=8, max=25)
    private String password;
}
