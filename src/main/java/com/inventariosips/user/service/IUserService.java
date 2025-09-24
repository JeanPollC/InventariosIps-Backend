package com.inventariosips.user.service;

import com.inventariosips.user.model.UserEntity;

import java.util.List;

public interface IUserService {

    UserEntity saveUser(UserEntity userEntity);
    UserEntity updateUser(UserEntity userEntity, Integer id);
    List<UserEntity> findAllUser();
    UserEntity findByIdUser(Integer id);
    void deleteUser(Integer id);
}
