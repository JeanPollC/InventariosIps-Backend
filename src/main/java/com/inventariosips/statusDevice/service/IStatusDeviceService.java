package com.inventariosips.statusDevice.service;

import com.inventariosips.statusDevice.model.StatusDeviceEntity;

import java.util.List;

public interface IStatusDeviceService {
    StatusDeviceEntity saveStatusDevice(StatusDeviceEntity statusDeviceEntity);
    StatusDeviceEntity updateStatusDevice(StatusDeviceEntity statusDeviceEntity, Integer id);
    List<StatusDeviceEntity> getAllStatusDevice();
    StatusDeviceEntity findByIDStatusDevice(Integer id);
    void deleteStatusDevice(Integer id);

}
