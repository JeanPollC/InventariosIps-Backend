package com.inventariosips.userType.mapper;

import com.inventariosips.userType.dto.UserTypeDTO;
import com.inventariosips.userType.model.UserTypeEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMapperUserType {

    UserTypeEntity userTypeDTOToUserTypeEntity(UserTypeDTO userTypeDTO);
    UserTypeDTO userTypeEntityToUserTypeDTO(UserTypeEntity userTypeEntity);
    List<UserTypeDTO> lstUserTypeEntityToLstUserTypeDTO(List<UserTypeEntity> userTypeEntity);
}
