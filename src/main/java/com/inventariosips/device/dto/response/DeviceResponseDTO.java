package com.inventariosips.device.dto.response;

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
public class DeviceResponseDTO {

    private Integer idDevice;

    @NotNull
    private String name;

    @NotNull
    private AreaDTO area;

    @NotNull
    private BrandDTO brand;

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
    private StatusDeviceDTO statusDevice;

    @NotNull
    private WarrantyDTO warranty;

    private String observation;

    private byte[] lifecycleFile;

}
