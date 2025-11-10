package com.inventariosips.userDevice.service;

import com.inventariosips.userDevice.model.UserDeviceEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface IUserDeviceService {

    UserDeviceEntity saveUserDevice(UserDeviceEntity userDeviceEntity);
    UserDeviceEntity updateUserDevice(UserDeviceEntity userDeviceEntity, Integer id);
    List<UserDeviceEntity> findAllUserDevice();
    UserDeviceEntity findByIdUserDevice(Integer id);
    void deleteUserDevice(UserDeviceEntity userDevice, Integer id);
    UserDeviceEntity closeAssignment(Integer idUserDevice, LocalDateTime deliveryDate);
}
