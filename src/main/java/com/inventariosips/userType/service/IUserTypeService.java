package com.inventariosips.userType.service;

import com.inventariosips.userType.model.UserTypeEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IUserTypeService {

    UserTypeEntity saveUserType(UserTypeEntity userTypeEntity);
    UserTypeEntity updateUserType(UserTypeEntity userTypeEntity, Integer id);
    List<UserTypeEntity> findAllUserType();
    UserTypeEntity findByIdUserType(Integer id);
    void deleteUserType(Integer id);
}
