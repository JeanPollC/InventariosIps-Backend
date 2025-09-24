package com.inventariosips.userType.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTypeDTO {

    private Integer idUserType;

    @NotNull
    @Size(min = 5)
    private String userType;
}
