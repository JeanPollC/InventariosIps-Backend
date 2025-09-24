package com.inventariosips.device.repo;

import com.inventariosips.device.model.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDeviceRepo extends JpaRepository<DeviceEntity, Integer> {
}
