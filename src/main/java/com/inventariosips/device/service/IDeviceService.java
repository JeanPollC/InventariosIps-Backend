package com.inventariosips.device.service;

import com.inventariosips.device.model.DeviceEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IDeviceService {

    DeviceEntity saveDevice(DeviceEntity deviceEntity);
    DeviceEntity updateDevice(DeviceEntity deviceEntity, Integer id);
    List<DeviceEntity> findAllDevice();
    DeviceEntity findByIdDevice(Integer id);
    void deleteDevice(Integer id);

    String getNameUserByNameDevice(String deviceName);
    String getNameUserByNameDeviceLoan(String deviceName);

    String uploadPdf(MultipartFile file, Integer idDevice) throws IOException;

    void updateDeviceStatus(Integer idDevice, Integer idStatusDevice);

    List<DeviceEntity> findAvailableDevices();
}
