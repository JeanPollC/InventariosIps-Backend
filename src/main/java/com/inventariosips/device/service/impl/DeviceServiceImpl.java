package com.inventariosips.device.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.inventariosips.device.model.DeviceEntity;
import com.inventariosips.device.repo.IDeviceRepo;
import com.inventariosips.device.service.IDeviceService;
import com.inventariosips.exception.ModelNotFoundException;
import com.inventariosips.loans.model.LoansEntity;
import com.inventariosips.statusDevice.model.StatusDeviceEntity;
import com.inventariosips.statusDevice.repo.IStatusDeviceRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DeviceServiceImpl implements IDeviceService {

    private final IDeviceRepo deviceRepo;
    private final Cloudinary cloudinary;
    private final IStatusDeviceRepo statusDeviceRepo;

    @Override
    public DeviceEntity saveDevice(DeviceEntity deviceEntity) {
        // Obtener el estado por defecto (ID = 1 → "Disponible")
        StatusDeviceEntity defaultStatus = statusDeviceRepo.findById(1)
                .orElseThrow(() -> new ModelNotFoundException("Estado por defecto no encontrado (ID = 1)"));

        if (deviceEntity.getStatusDevice() == null) {
            deviceEntity.setStatusDevice(defaultStatus);
        }

        return deviceRepo.save(deviceEntity);
    }

    @Override
    public DeviceEntity updateDevice(DeviceEntity DeviceEntity, Integer id) {
        deviceRepo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
        return deviceRepo.save(DeviceEntity);
    }

    @Override
    public List<DeviceEntity> findAllDevice() {
        return deviceRepo.findAll();
    }

    @Override
    public DeviceEntity findByIdDevice(Integer id) {
        return deviceRepo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
    }

    @Override
    public void deleteDevice(Integer id) {
        deviceRepo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
        deviceRepo.deleteById(id);
    }

    @Override
    public String getNameUserByNameDevice(String deviceName) {
        return deviceRepo.getNameUserByNameDevice(deviceName);
    }


    @Override
    public String getNameUserByNameDeviceLoan(String deviceName) {
        return deviceRepo.getNameUserByNameDeviceLoan(deviceName);
    }

    @Override
    public String uploadPdf(MultipartFile file, Integer deviceId) throws IOException {
        // Obtener el nombre original del archivo (incluye .pdf)
        String originalFilename = file.getOriginalFilename();

        // Opcional: quitar espacios o caracteres especiales
        String publicId = originalFilename != null ? originalFilename.replaceAll("\\s+", "_") : "documento.pdf";

        Map uploadResult = cloudinary.uploader().upload(file.getBytes(),
                ObjectUtils.asMap(
                        "resource_type", "raw",
                        "type", "upload",
                        "folder", "hojas_vida_equipos/" + deviceId,
                        "public_id", publicId,  // 👈 Asegura que se guarde con nombre y extensión
                        "use_filename", true,   // 👈 Conserva el nombre del archivo
                        "unique_filename", false // 👈 Evita que Cloudinary lo renombre
                ));
        String url = uploadResult.get("secure_url").toString();

        DeviceEntity device = deviceRepo.findById(deviceId)
                .orElseThrow( () -> new RuntimeException("device no encontrado"));
        device.setLifecycleFile(url);
        deviceRepo.save(device);

        return url;
    }

    @Override
    public void updateDeviceStatus(Integer idDevice, Integer idStatusDevice) {
        DeviceEntity device = deviceRepo.findById(idDevice)
                .orElseThrow(() -> new ModelNotFoundException("Dispositivo no encontrado: " + idDevice));

        StatusDeviceEntity status = statusDeviceRepo.findByIdStatusDevice(idStatusDevice)
                .orElseThrow(() -> new ModelNotFoundException("Estado no encontrado "));

        device.setStatusDevice(status);
        deviceRepo.save(device);
    }

    @Override
    public List<DeviceEntity> findAvailableDevices() {
        return deviceRepo.findByStatusDevice_NameStatus("Disponible");
    }


}
