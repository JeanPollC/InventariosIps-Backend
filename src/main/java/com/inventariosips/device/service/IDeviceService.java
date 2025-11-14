package com.inventariosips.device.service;

import com.inventariosips.device.model.DeviceEntity;
import com.inventariosips.user.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IDeviceService {

    DeviceEntity saveDevice(DeviceEntity deviceEntity);
    DeviceEntity updateDevice(DeviceEntity deviceEntity, Integer id);
    List<DeviceEntity> findAllDevice();
    Page<DeviceEntity> findAllDevice(Pageable pageable, String filter) throws Exception;
    DeviceEntity findByIdDevice(Integer id);
    void deleteDevice(Integer id);

    String getNameUserByNameDevice(String deviceName);
    String getNameUserByNameDeviceLoan(String deviceName);

    String uploadPdf(MultipartFile file, Integer idDevice) throws IOException;

    void updateDeviceStatus(Integer idDevice, Integer idStatusDevice);

    List<DeviceEntity> findAvailableDevices();
}
