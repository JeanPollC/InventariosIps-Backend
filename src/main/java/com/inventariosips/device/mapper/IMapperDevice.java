package com.inventariosips.device.mapper;


import com.inventariosips.brand.model.BrandEntity;
import com.inventariosips.device.dto.DeviceDTO;
import com.inventariosips.device.model.DeviceEntity;
import com.inventariosips.statusDevice.model.StatusDeviceEntity;
import com.inventariosips.warranty.model.WarrantyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMapperDevice {

    @Mapping(source = "idBrand", target = "brand")
    @Mapping(source = "idWarranty", target = "warranty")
    @Mapping(source = "idStatusDevice", target = "statusDevice")
    DeviceEntity DeviceDTOToDeviceEntity(DeviceDTO deviceDTO);

    @Mapping(source = "brand.idBrand", target = "idBrand")
    @Mapping(source = "warranty.idWarranty", target = "idWarranty")
    @Mapping(source = "statusDevice.idStatusDevice", target = "idStatusDevice")
    DeviceDTO DeviceEntityToDeviceDTO(DeviceEntity deviceEntity);

    List<DeviceDTO> lstDeviceEntityToLstDeviceDTO(List<DeviceEntity> devicesListEntity);

    default BrandEntity mapBrand(Integer id) {
        if (id == null) return null;
        BrandEntity brand = new BrandEntity();
        brand.setIdBrand(id);
        return brand;
    }

    default WarrantyEntity mapWarranty(Integer id) {
        if (id == null) return null;
        WarrantyEntity warranty = new WarrantyEntity();
        warranty.setIdWarranty(id);
        return warranty;
    }

    default StatusDeviceEntity mapStatusDevice(Integer id) {
        if (id == null) return null;
        StatusDeviceEntity status = new StatusDeviceEntity();
        status.setIdStatusDevice(id);
        return status;
    }
}
