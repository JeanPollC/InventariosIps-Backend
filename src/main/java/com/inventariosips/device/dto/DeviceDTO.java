package com.inventariosips.device.dto;

import com.inventariosips.statusDevice.model.StatusDeviceEntity;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceDTO {

    private Integer idDevice;

    @NotNull
    @Size(min = 8)
    private String password;

    @NotNull
    private Integer idBrand;

    @NotNull
    @Size(min = 10)
    private String description;

    @NotNull
    @Size(min = 5)
    private String deviceType;

    private Integer idAssignedUser;

    @NotNull
    private Integer idWarranty;

    @Column(nullable = false)
    private LocalDate purchaseDate;

    @NotNull
    private Integer idStatusDevice;

    private byte[] lifecycleFile;
}
