package com.inventariosips.UserDevice.mapper;

import com.inventariosips.UserDevice.dto.UserDeviceDTO;
import com.inventariosips.UserDevice.model.UserDeviceEntity;
import com.inventariosips.device.model.DeviceEntity;
import com.inventariosips.user.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMapperUserDevice {

   @Mapping(source = "idUser", target = "user")
   @Mapping(source = "idDevice", target = "device")
    UserDeviceEntity userDeviceDTOToUserDeviceEntity(UserDeviceDTO userDeviceDTO);

    @Mapping(source = "user.idUser", target = "idUser")
    @Mapping(source = "device.idDevice", target = "idDevice")
    UserDeviceDTO userDeviceEntityToUserDeviceDTO(UserDeviceEntity userDeviceEntity);

    List<UserDeviceDTO> lstUserDeviceEntityToLstUserDeviceDTO(List<UserDeviceEntity> userDeviceEntity);

    default UserEntity mapUser(Integer idUser){
        if (idUser == null) return null;
        UserEntity user = new UserEntity();
        user.setIdUser(idUser);
        return user;
    }

    default DeviceEntity mapDevice(Integer idDevice){
        if (idDevice == null) return null;
        DeviceEntity device = new DeviceEntity();
        device.setIdDevice(idDevice);
        return device;
    }

}
