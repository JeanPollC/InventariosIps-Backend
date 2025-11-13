package com.inventariosips.device.mapper;


import com.inventariosips.area.model.AreaEntity;
import com.inventariosips.brand.model.BrandEntity;
import com.inventariosips.device.dto.request.DeviceRequestDTO;
import com.inventariosips.device.dto.response.DeviceResponseDTO;
import com.inventariosips.device.model.DeviceEntity;
import com.inventariosips.statusDevice.model.StatusDeviceEntity;
import com.inventariosips.warranty.mapper.IMapperWarranty;
import com.inventariosips.warranty.model.WarrantyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {IMapperWarranty.class})
public interface IMapperDevice {

    //RESPONSE
    DeviceResponseDTO DeviceEntityToDeviceResponseDTO(DeviceEntity deviceEntity);

    List<DeviceResponseDTO> lstDeviceEntityToLstDeviceResponseDTO(List<DeviceEntity> devicesListEntity);


    //REQUEST
    @Mapping(source = "idArea", target = "area", qualifiedByName = "mapArea")
    @Mapping(source = "idBrand", target = "brand", qualifiedByName = "mapBrand")
    @Mapping(source = "idStatusDevice", target = "statusDevice", qualifiedByName = "mapStatusDevice")
    @Mapping(source = "idWarranty", target = "warranty", qualifiedByName = "mapWarranty")
    DeviceEntity DeviceRequestDTOToDeviceEntity(DeviceRequestDTO deviceDTO);


    @org.mapstruct.Named("mapArea")
    default AreaEntity mapArea(Integer id) {
        if (id == null) return null;
        AreaEntity area = new AreaEntity();
        area.setIdArea(id);
        return area;
    }

    @org.mapstruct.Named("mapBrand")
    default BrandEntity mapBrand(Integer id) {
        if (id == null) return null;
        BrandEntity brand = new BrandEntity();
        brand.setIdBrand(id);
        return brand;
    }

    @org.mapstruct.Named("mapStatusDevice")
    default StatusDeviceEntity mapStatusDevice(Integer id) {
        if (id == null) return null;
        StatusDeviceEntity status = new StatusDeviceEntity();
        status.setIdStatusDevice(id);
        return status;
    }

    @org.mapstruct.Named("mapWarranty")
    default WarrantyEntity mapWarranty(Integer id) {
        if (id == null) return null;
        WarrantyEntity warranty = new WarrantyEntity();
        warranty.setIdWarranty(id);
        return warranty;
    }
}
