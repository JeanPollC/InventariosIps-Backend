package com.inventariosips.device.service;

import com.inventariosips.device.model.DeviceEntity;

import java.util.List;

public interface IDeviceService {

    DeviceEntity saveDevice(DeviceEntity deviceEntity);
    DeviceEntity updateDevice(DeviceEntity deviceEntity, Integer id);
    List<DeviceEntity> findAllDevice();
    DeviceEntity findByIdDevice(Integer id);
    void deleteDevice(Integer id);
}
