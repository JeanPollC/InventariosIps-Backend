package com.inventariosips.user.dto;

import com.inventariosips.userType.model.UserTypeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Integer idUser;

    @NotNull
    private String name;

    @NotNull
    private String lastName;

    @NotNull
    private Integer idUserType;

    @Email
    private String email;

    @NotNull
    private String status;
}
