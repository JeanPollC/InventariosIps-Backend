package com.inventariosips.user.dto.response;

import com.inventariosips.area.dto.AreaDTO;
import com.inventariosips.brand.dto.BrandDTO;
import com.inventariosips.statusDevice.dto.StatusDeviceDTO;
import com.inventariosips.userType.dto.UserTypeDTO;
import com.inventariosips.warranty.dto.WarrantyDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private Integer idUser;

    @NotNull
    private String name;

    @NotNull
    private String lastName;

    @Email
    private String email;

    @NotNull
    private UserTypeDTO UserType;

    @NotNull
    private String status;
}
