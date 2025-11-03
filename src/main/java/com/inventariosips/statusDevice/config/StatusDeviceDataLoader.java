package com.inventariosips.statusDevice.config;

import com.inventariosips.statusDevice.model.StatusDeviceEntity;
import com.inventariosips.statusDevice.repo.IStatusDeviceRepo;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StatusDeviceDataLoader implements CommandLineRunner {

    private final IStatusDeviceRepo statusDeviceRepo;

    @Override
    public void run(String... args) {
        createIfNotExists("Disponible", true);
        createIfNotExists("Asignado", true);
        createIfNotExists("Prestado", true);
    }

    private void createIfNotExists(String nameStatus, boolean locked) {
        statusDeviceRepo.findByNameStatus(nameStatus)
                .orElseGet(() -> statusDeviceRepo.save(
                        new StatusDeviceEntity(null, nameStatus, locked)
                ));
    }
}
