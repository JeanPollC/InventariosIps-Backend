package com.inventariosips.userDevice.repo;


import com.inventariosips.userDevice.model.UserDeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDeviceRepo extends JpaRepository<UserDeviceEntity, Integer> {
}
