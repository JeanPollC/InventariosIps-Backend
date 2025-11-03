package com.inventariosips.statusDevice.repo;

import com.inventariosips.statusDevice.model.StatusDeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IStatusDeviceRepo extends JpaRepository<StatusDeviceEntity, Integer> {

    Optional<StatusDeviceEntity> findByIdStatusDevice(Integer id);
    Optional<StatusDeviceEntity> findByNameStatus(String name);
}
