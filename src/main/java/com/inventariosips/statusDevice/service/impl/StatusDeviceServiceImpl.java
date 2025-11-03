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
        // No se permiten estados fijos creados manualmente desde el CRUD
        statusDeviceEntity.setIsFixed(false);
        return statusDeviceRepo.save(statusDeviceEntity);
    }

    @Override
    public StatusDeviceEntity updateStatusDevice(StatusDeviceEntity statusDeviceEntity, Integer id) {
        StatusDeviceEntity existing = statusDeviceRepo.findById(id)
                .orElseThrow(() ->new ModelNotFoundException("ID NOT FOUND: " + id));

        if (existing.getIsFixed()){
            throw new IllegalStateException("No se puede modificar un estado fijo del sistema.");
        }

        statusDeviceEntity.setIdStatusDevice(id);
        statusDeviceEntity.setIsFixed(false); // forzar a que no cambie
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
        StatusDeviceEntity entity = statusDeviceRepo.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));

        if (entity.getIsFixed()){
            throw new IllegalStateException("No se puede eliminar un estado fijo del sistema");
        }
        statusDeviceRepo.deleteById(id);
    }
}
