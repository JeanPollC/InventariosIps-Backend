package com.inventariosips.user.service;

import com.inventariosips.user.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {

    UserEntity saveUser(UserEntity userEntity);
    UserEntity updateUser(UserEntity userEntity, Integer id);
    List<UserEntity> findAllUser();
    Page<UserEntity> findAllUser(Pageable pageable, String filter) throws Exception;
    UserEntity findByIdUser(Integer id);
    void deleteUser(Integer id);
}
