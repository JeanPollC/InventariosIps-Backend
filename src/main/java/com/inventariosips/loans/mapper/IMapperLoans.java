package com.inventariosips.loans.mapper;

import com.inventariosips.device.model.DeviceEntity;
import com.inventariosips.loans.dto.LoansDTO;
import com.inventariosips.loans.model.LoansEntity;
import com.inventariosips.user.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMapperLoans {

    @Mapping(source = "idUser", target = "userEntity")
    @Mapping(source = "idDevice", target = "deviceEntity")
    LoansEntity loansDTOToLoansEntity(LoansDTO loansDTO);

    @Mapping(source = "userEntity.idUser", target = "idUser")
    @Mapping(source = "deviceEntity.idDevice", target = "idDevice")
    LoansDTO loansEntityToLoansDTO(LoansEntity loansEntity);

    List<LoansDTO> lstLoansEntityToLstLoansDTO(List<LoansEntity> loansEntity);

    default UserEntity mapUser(Integer idUser) {
        if (idUser == null){
            return null;
        }
        UserEntity user = new UserEntity();
        user.setIdUser(idUser);
        return user;
    }

    default DeviceEntity mapDevice(Integer idDevice) {
        if (idDevice == null){
            return null;
        }
        DeviceEntity device = new DeviceEntity();
        device.setIdDevice(idDevice);
        return device;
    }



}
