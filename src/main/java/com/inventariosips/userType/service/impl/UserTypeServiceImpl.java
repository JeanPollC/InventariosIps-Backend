package com.inventariosips.userType.service.impl;

import com.inventariosips.exception.ModelNotFoundException;
import com.inventariosips.userType.model.UserTypeEntity;
import com.inventariosips.userType.repo.IUserTypeRepo;
import com.inventariosips.userType.service.IUserTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserTypeServiceImpl implements IUserTypeService {

    private final IUserTypeRepo userTypeRepo;

    @Override
    public UserTypeEntity saveUserType(UserTypeEntity userTypeEntity) {
        return userTypeRepo.save(userTypeEntity);
    }

    @Override
    public UserTypeEntity updateUserType(UserTypeEntity userTypeEntity, Integer id) {
        userTypeRepo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
        return userTypeRepo.save(userTypeEntity);
    }

    @Override
    public List<UserTypeEntity> findAllUserType() {
        return userTypeRepo.findAll();
    }

    @Override
    public UserTypeEntity findByIdUserType(Integer id) {
        return userTypeRepo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
    }

    @Override
    public void deleteUserType(Integer id) {
        userTypeRepo.deleteById(id);
    }
}
