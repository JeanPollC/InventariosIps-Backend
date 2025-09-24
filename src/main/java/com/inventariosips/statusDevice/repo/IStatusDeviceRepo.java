package com.inventariosips.statusDevice.repo;

import com.inventariosips.statusDevice.model.StatusDeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStatusDeviceRepo extends JpaRepository<StatusDeviceEntity, Integer> {
}
