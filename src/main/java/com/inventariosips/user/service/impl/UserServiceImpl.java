package com.inventariosips.user.service.impl;

import com.inventariosips.exception.ModelNotFoundException;
import com.inventariosips.user.model.UserEntity;
import com.inventariosips.user.repo.IUserRepo;
import com.inventariosips.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUserRepo userRepo;

    @Override
    public UserEntity saveUser(UserEntity userEntity) {
        return userRepo.save(userEntity);
    }

    @Override
    public UserEntity updateUser(UserEntity userEntity, Integer id) {
        userRepo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
        return userRepo.save(userEntity);
    }

    @Override
    public List<UserEntity> findAllUser() {
        return userRepo.findAll();
    }

    @Override
    public UserEntity findByIdUser(Integer id) {
        return userRepo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
    }

    @Override
    public void deleteUser(Integer id) {
        userRepo.deleteById(id);
    }
}
