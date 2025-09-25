package com.inventariosips.UserDevice.service.impl;

import com.inventariosips.exception.ModelNotFoundException;
import com.inventariosips.UserDevice.model.UserDeviceEntity;
import com.inventariosips.UserDevice.repo.IUserDeviceRepo;
import com.inventariosips.UserDevice.service.IUserDeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDeviceServiceImpl implements IUserDeviceService {

    private final IUserDeviceRepo userDeviceRepo;

    @Override
    public UserDeviceEntity saveUserDevice(UserDeviceEntity userDeviceEntity) {
        return userDeviceRepo.save(userDeviceEntity);
    }

    @Override
    public UserDeviceEntity updateUserDevice(UserDeviceEntity userDeviceEntity, Integer id) {
        userDeviceRepo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
        return userDeviceRepo.save(userDeviceEntity);
    }

    @Override
    public List<UserDeviceEntity> findAllUserDevice() {
        return userDeviceRepo.findAll();
    }

    @Override
    public UserDeviceEntity findByIdUserDevice(Integer id) {
        return userDeviceRepo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
    }

    @Override
    public void deleteUserDevice(Integer id) {
        userDeviceRepo.deleteById(id);
    }
}
