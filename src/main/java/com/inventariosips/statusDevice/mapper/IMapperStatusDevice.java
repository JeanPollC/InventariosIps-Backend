package com.inventariosips.statusDevice.mapper;

import com.inventariosips.statusDevice.dto.StatusDeviceDTO;
import com.inventariosips.statusDevice.model.StatusDeviceEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMapperStatusDevice {

    StatusDeviceEntity statusDeviceDTOToStatusDeviceEntity(StatusDeviceDTO statusDeviceDTO);
    StatusDeviceDTO statusDeviceEntityToStatusDeviceDTO(StatusDeviceEntity statusDeviceEntity);
    List<StatusDeviceDTO> lstStatusDeviceEntityToListStatusDeviceDTO(List<StatusDeviceEntity> lstStatusDeviceEntity);

}
