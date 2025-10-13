package com.inventariosips.device.dto.request;

import com.inventariosips.area.dto.AreaDTO;
import com.inventariosips.brand.dto.BrandDTO;
import com.inventariosips.statusDevice.dto.StatusDeviceDTO;
import com.inventariosips.warranty.dto.WarrantyDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceRequestDTO {

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
