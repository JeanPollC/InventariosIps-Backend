package com.inventariosips.loans.mapper;

import com.inventariosips.device.model.DeviceEntity;
import com.inventariosips.loans.dto.request.LoansRequestDTO;
import com.inventariosips.loans.dto.response.LoansResponseDTO;
import com.inventariosips.loans.model.LoansEntity;
import com.inventariosips.user.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMapperLoans {

    //RESPONSE
    LoansResponseDTO LoansEntityToLoansResponseDTO(LoansEntity loansEntity);

    List<LoansResponseDTO> lstLoansEntityToLstLoansResponseDTO(List<LoansEntity> loansListEntity);

    //REQUEST
    @Mapping(source = "idUser", target = "user")
    @Mapping(source = "idDevice", target = "device")
    LoansEntity loansDTOToLoansEntity(LoansRequestDTO loansDTO);

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
