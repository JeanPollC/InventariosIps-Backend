package com.inventariosips.userDevice.controller;

import com.inventariosips.userDevice.dto.request.UserDeviceRequestDTO;
import com.inventariosips.userDevice.dto.response.UserDeviceResponseDTO;
import com.inventariosips.userDevice.mapper.IMapperUserDevice;
import com.inventariosips.userDevice.model.UserDeviceEntity;
import com.inventariosips.userDevice.service.IUserDeviceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
    @RequestMapping("usersDevices")
@RequiredArgsConstructor
public class UserDeviceController {

    private final IUserDeviceService userDeviceService;
    private final IMapperUserDevice mapperUserDevice;

    @GetMapping
    public ResponseEntity<List<UserDeviceResponseDTO>> findAllUserDevicesTypes() throws Exception {
        List<UserDeviceEntity> lst = userDeviceService.findAllUserDevice().stream().toList();
        return ResponseEntity.ok(mapperUserDevice.lstUserDeviceEntityToLstUserDeviceResponseDTO(lst));
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDeviceResponseDTO> findByIdUserDevice(@PathVariable("id") Integer id) throws Exception {
        UserDeviceResponseDTO dto = mapperUserDevice.UserDeviceEntityToUserDeviceResponseDTO(userDeviceService.findByIdUserDevice(id));

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<UserDeviceEntity> saveUserDevice(@Valid @RequestBody UserDeviceRequestDTO userDeviceDTO) throws Exception{
        UserDeviceEntity userDeviceEntity = userDeviceService.saveUserDevice(mapperUserDevice.userDeviceDTOToUserDeviceEntity(userDeviceDTO));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userDeviceEntity.getIdUserDevice()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDeviceEntity> updateUserDevice(@Valid @RequestBody UserDeviceRequestDTO userDeviceDTO, @PathVariable("id") Integer id) throws Exception {
        userDeviceDTO.setIdUserDevice(id);
        UserDeviceEntity userDeviceEntity = userDeviceService.updateUserDevice(mapperUserDevice.userDeviceDTOToUserDeviceEntity(userDeviceDTO), id);

        return ResponseEntity.ok(userDeviceEntity);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUserDevice(@PathVariable("id") Integer id) throws Exception {
        userDeviceService.deleteUserDevice(id);

        return ResponseEntity.noContent().build();
    }

}
