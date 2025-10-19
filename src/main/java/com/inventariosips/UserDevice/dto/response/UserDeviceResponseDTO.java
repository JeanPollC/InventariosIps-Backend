package com.inventariosips.UserDevice.dto.response;

import com.inventariosips.device.model.DeviceEntity;
import com.inventariosips.user.model.UserEntity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDeviceResponseDTO {

    private Integer idUserDevice;

    @NotNull
    private UserEntity user;

    @NotNull
    private DeviceEntity device;

    @NotNull
    private LocalDateTime assignmentDate;

    private LocalDateTime deliveryDate;

    @NotNull
    private String status;
}