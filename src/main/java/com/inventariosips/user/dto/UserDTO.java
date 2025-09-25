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

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

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
