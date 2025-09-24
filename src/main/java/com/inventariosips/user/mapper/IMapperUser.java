package com.inventariosips.user.mapper;

import com.inventariosips.user.dto.UserDTO;
import com.inventariosips.user.model.UserEntity;
import com.inventariosips.userType.model.UserTypeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMapperUser {

    @Mapping(source = "idUserType", target = "userType")
    UserEntity userDTOToUserEntity(UserDTO userDTO);

    @Mapping(source = "userType.idUserType", target = "idUserType")
    UserDTO userEntityToUserDTO(UserEntity userEntity);

    List<UserDTO> lstUserEntityToLstUserDTO(List<UserEntity> userEntity);

    default UserTypeEntity map(Integer idUserType) {
        if (idUserType == null) {
            return null;
        }
        UserTypeEntity userType = new UserTypeEntity();
        userType.setIdUserType(idUserType);
        return userType;
    }
}
