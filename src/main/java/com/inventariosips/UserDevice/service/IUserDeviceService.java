package com.inventariosips.UserDevice.service;

import com.inventariosips.UserDevice.model.UserDeviceEntity;

import java.util.List;

public interface IUserDeviceService {

    UserDeviceEntity saveUserDevice(UserDeviceEntity userDeviceEntity);
    UserDeviceEntity updateUserDevice(UserDeviceEntity userDeviceEntity, Integer id);
    List<UserDeviceEntity> findAllUserDevice();
    UserDeviceEntity findByIdUserDevice(Integer id);
    void deleteUserDevice(Integer id);
}
