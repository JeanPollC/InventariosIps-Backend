package com.inventariosips.UserDevice.repo;


import com.inventariosips.UserDevice.model.UserDeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDeviceRepo extends JpaRepository<UserDeviceEntity, Integer> {
}
