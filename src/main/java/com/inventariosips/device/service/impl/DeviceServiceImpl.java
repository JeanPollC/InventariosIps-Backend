package com.inventariosips.device.service.impl;

import com.inventariosips.device.model.DeviceEntity;
import com.inventariosips.device.repo.IDeviceRepo;
import com.inventariosips.device.service.IDeviceService;
import com.inventariosips.exception.ModelNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeviceServiceImpl implements IDeviceService {

    private final IDeviceRepo deviceRepo;

    @Override
    public DeviceEntity saveDevice(DeviceEntity DeviceEntity) {
        return deviceRepo.save(DeviceEntity);
    }

    @Override
    public DeviceEntity updateDevice(DeviceEntity DeviceEntity, Integer id) {
        deviceRepo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
        return deviceRepo.save(DeviceEntity);
    }

    @Override
    public List<DeviceEntity> findAllDevice() {
        return deviceRepo.findAll();
    }

    @Override
    public DeviceEntity findByIdDevice(Integer id) {
        return deviceRepo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
    }

    @Override
    public void deleteDevice(Integer id) {
        deviceRepo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
        deviceRepo.deleteById(id);
    }

    @Override
    public String getNameUserByNameDevice(String deviceName) {
        return deviceRepo.getNameUserByNameDevice(deviceName);
    }
}
