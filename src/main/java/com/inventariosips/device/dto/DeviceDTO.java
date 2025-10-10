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
    private String name;

    @NotNull
    private Integer idArea;

    @NotNull
    private Integer idBrand;

    @NotNull
    private String deviceType;

    private String storage;

    private String graphics_card;

    private String ram;

    private String processor;

    @NotNull
    private String product_code;

    @NotNull
    private String serial_no;

    private String windows_edition;

    @NotNull
    private Integer idStatusDevice;

    @NotNull
    private Integer idWarranty;

    private String observation;

    private byte[] lifecycleFile;

}
