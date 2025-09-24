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

    private final IDeviceRepo DeviceRepo;

    @Override
    public DeviceEntity saveDevice(DeviceEntity DeviceEntity) {
        return DeviceRepo.save(DeviceEntity);
    }

    @Override
    public DeviceEntity updateDevice(DeviceEntity DeviceEntity, Integer id) {
        DeviceRepo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
        return DeviceRepo.save(DeviceEntity);
    }

    @Override
    public List<DeviceEntity> findAllDevice() {
        return DeviceRepo.findAll();
    }

    @Override
    public DeviceEntity findByIdDevice(Integer id) {
        return DeviceRepo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
    }

    @Override
    public void deleteDevice(Integer id) {
        DeviceRepo.deleteById(id);
    }
}
