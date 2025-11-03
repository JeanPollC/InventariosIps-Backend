package com.inventariosips.userDevice.mapper;

import com.inventariosips.userDevice.dto.request.UserDeviceRequestDTO;
import com.inventariosips.userDevice.dto.response.UserDeviceResponseDTO;
import com.inventariosips.userDevice.model.UserDeviceEntity;
import com.inventariosips.device.model.DeviceEntity;
import com.inventariosips.user.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMapperUserDevice {

    //RESPONSE
    UserDeviceResponseDTO UserDeviceEntityToUserDeviceResponseDTO(UserDeviceEntity userDeviceEntity);

    List<UserDeviceResponseDTO> lstUserDeviceEntityToLstUserDeviceResponseDTO(List<UserDeviceEntity> usersDeviceListEntity);

    //REQUEST
    @Mapping(source = "idUser", target = "user")
    @Mapping(source = "idDevice", target = "device")
    UserDeviceEntity userDeviceDTOToUserDeviceEntity(UserDeviceRequestDTO userDeviceDTO);

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
