package com.inventariosips.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

    private Integer idUser;

    @NotNull
    private String name;

    @NotNull
    private String lastName;

    @Email
    private String email;

    @NotNull
    private Integer idUserType;

    @NotNull
    private String status;
}

