package com.inventariosips.userDevice.service.impl;

import com.inventariosips.device.model.DeviceEntity;
import com.inventariosips.device.service.IDeviceService;
import com.inventariosips.exception.ModelNotFoundException;
import com.inventariosips.loans.model.LoansEntity;
import com.inventariosips.user.model.UserEntity;
import com.inventariosips.userDevice.model.UserDeviceEntity;
import com.inventariosips.userDevice.repo.IUserDeviceRepo;
import com.inventariosips.userDevice.service.IUserDeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.IllegalFormatCodePointException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDeviceServiceImpl implements IUserDeviceService {

    private final IUserDeviceRepo userDeviceRepo;
    private final IDeviceService deviceService;

    @Override
    public UserDeviceEntity saveUserDevice(UserDeviceEntity userDevice) {

        DeviceEntity device = deviceService.findByIdDevice(userDevice.getDevice().getIdDevice());

        if (device == null) {
            throw new ModelNotFoundException("Dispositivo no encontrado con ID: " + userDevice.getDevice().getIdDevice());
        }

        userDevice.setDevice(device);

        if (userDevice.getStatus() == null && userDevice.getDeliveryDate() == null || userDevice.getDeliveryDate().isAfter(LocalDateTime.now())) {
            userDevice.setStatus("Activo");
        } else {
            userDevice.setStatus(("Entregado"));
        }

        // Aquí decides si es asignación o préstamo, según el tipo
        if (userDevice.getDevice().getStatusDevice().getNameStatus().equals("Disponible")) {
            UserDeviceEntity saved = userDeviceRepo.save(userDevice);
            deviceService.updateDeviceStatus(userDevice.getDevice().getIdDevice(), 2);//SE PASA A ESTADO ASIGNADO
            if (userDevice.getDeliveryDate() != null && !userDevice.getDeliveryDate().isAfter(LocalDateTime.now())){
                return closeAssignment(saved.getIdUserDevice(), saved.getDeliveryDate());
            }
            return saved;
        } else {
            throw new IllegalStateException("El dispositivo no está disponible para asignar.");
        }
    }

    @Override
    public UserDeviceEntity updateUserDevice(UserDeviceEntity userDeviceEntity, Integer id) {
        UserDeviceEntity existing = userDeviceRepo.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Asignación no encontrada con ID: " + id));


        // 🔹 Actualizar fecha de asignación
        if (userDeviceEntity.getAssignmentDate() != null) {
            existing.setAssignmentDate(userDeviceEntity.getAssignmentDate());
            deviceService.updateDeviceStatus(existing.getDevice().getIdDevice(), 2);
        }

        // Actualizar solo los campos que pueden cambiar
        if (userDeviceEntity.getDeliveryDate() != null) {
            existing.setDeliveryDate(userDeviceEntity.getDeliveryDate());
            // 🔹 Si la fecha de entrega ya pasó o es igual a ahora, cerrar la asignación
            if (userDeviceEntity.getDeliveryDate().isAfter(LocalDateTime.now())) {
                existing.setStatus("Activo");
            } else {
                return closeAssignment(id, userDeviceEntity.getDeliveryDate());
            }
        }

        if (userDeviceEntity.getUser() != null && userDeviceEntity.getUser().getIdUser() != null){
            if (!userDeviceEntity.getUser().getIdUser().equals(existing.getUser().getIdUser())){
                existing.setUser(userDeviceEntity.getUser());
            }
        }

        if (userDeviceEntity.getDevice() != null && userDeviceEntity.getDevice().getIdDevice() != null){
            if (!userDeviceEntity.getDevice().getIdDevice().equals(existing.getDevice().getIdDevice())){
                existing.setDevice(userDeviceEntity.getDevice());
                deviceService.updateDeviceStatus(userDeviceEntity.getDevice().getIdDevice(), 2);
            }
        }

        return userDeviceRepo.save(existing);
    }

    @Override
    public List<UserDeviceEntity> findAllUserDevice() {
        return userDeviceRepo.findAll();
    }

    @Override
    public Page<UserDeviceEntity> findAllUserDevice(Pageable pageable, String filter) {
        if (filter != null && !filter.trim().isEmpty()) {
            return userDeviceRepo.findByGlobalFilter(filter.toLowerCase(), pageable);
        }
        return userDeviceRepo.findAll(pageable);
    }

    @Override
    public UserDeviceEntity findByIdUserDevice(Integer id) {
        return userDeviceRepo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
    }

    @Override
    public void deleteUserDevice(UserDeviceEntity userDevice, Integer id) {
        userDeviceRepo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
        userDeviceRepo.deleteById(id);
        deviceService.updateDeviceStatus(userDevice.getDevice().getIdDevice(), 1);
    }


    @Override
    public UserDeviceEntity closeAssignment(Integer idUserDevice, LocalDateTime deliveryDate) {
        UserDeviceEntity userDevice = userDeviceRepo.findById(idUserDevice)
                .orElseThrow(()-> new ModelNotFoundException("Asignación no encontrada"));

        // Si el usuario envía una fecha, úsala. Si no, toma la actual.
        LocalDateTime endDate  =
                deliveryDate != null ? deliveryDate : LocalDateTime.now();

        userDevice.setStatus("Entregado");
        userDevice.setDeliveryDate(endDate);
        userDeviceRepo.save(userDevice);

        // Si la fecha ya llegó o ya pasó, liberar el dispositivo
        if (!endDate.isAfter(LocalDateTime.now())){
            deviceService.updateDeviceStatus(userDevice.getDevice().getIdDevice(), 1);
        }

        return userDevice;
    }
}
