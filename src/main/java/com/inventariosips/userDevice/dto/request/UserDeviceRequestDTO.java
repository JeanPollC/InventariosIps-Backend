package com.inventariosips.userDevice.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDeviceRequestDTO {

    private Integer idUserDevice;

    @NotNull
    private Integer idUser;

    @NotNull
    private Integer idDevice;

    @NotNull
    private LocalDateTime assignmentDate;

    private LocalDateTime deliveryDate;

    private String status;
}

