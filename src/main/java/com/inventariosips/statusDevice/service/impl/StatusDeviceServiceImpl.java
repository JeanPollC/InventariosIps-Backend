package com.inventariosips.statusDevice.service.impl;

import com.inventariosips.exception.ModelNotFoundException;
import com.inventariosips.statusDevice.model.StatusDeviceEntity;
import com.inventariosips.statusDevice.repo.IStatusDeviceRepo;
import com.inventariosips.statusDevice.service.IStatusDeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatusDeviceServiceImpl implements IStatusDeviceService {

    private final IStatusDeviceRepo statusDeviceRepo;

    @Override
    public StatusDeviceEntity saveStatusDevice(StatusDeviceEntity statusDeviceEntity) {
        return statusDeviceRepo.save(statusDeviceEntity);
    }

    @Override
    public StatusDeviceEntity updateStatusDevice(StatusDeviceEntity statusDeviceEntity, Integer id) {
        statusDeviceRepo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
        return statusDeviceRepo.save(statusDeviceEntity);
    }

    @Override
    public List<StatusDeviceEntity> getAllStatusDevice() {
        return statusDeviceRepo.findAll();
    }

    @Override
    public StatusDeviceEntity findByIDStatusDevice(Integer id) {
        return statusDeviceRepo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
    }

    @Override
    public void deleteStatusDevice(Integer id) {
        statusDeviceRepo.deleteById(id);
    }
}
