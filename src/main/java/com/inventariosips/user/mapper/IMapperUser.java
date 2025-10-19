package com.inventariosips.user.mapper;

import com.inventariosips.user.dto.request.UserRequestDTO;
import com.inventariosips.user.dto.response.UserResponseDTO;
import com.inventariosips.user.model.UserEntity;
import com.inventariosips.userType.model.UserTypeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMapperUser {

    //RESPONSE
    UserResponseDTO UserEntityToUserResponseDTO(UserEntity userEntity);

    List<UserResponseDTO> lstUserEntityToLstUserResponseDTO(List<UserEntity> usersListEntity);

    //REQUEST
    @Mapping(source = "idUserType", target = "userType")
    UserEntity userDTOToUserEntity(UserRequestDTO userDTO);


    default UserTypeEntity map(Integer idUserType) {
        if (idUserType == null) {
            return null;
        }
        UserTypeEntity userType = new UserTypeEntity();
        userType.setIdUserType(idUserType);
        return userType;
    }
}
