package com.inventariosips.userDevice.controller;

import com.inventariosips.user.dto.response.UserResponseDTO;
import com.inventariosips.user.model.UserEntity;
import com.inventariosips.userDevice.dto.request.UserDeviceRequestDTO;
import com.inventariosips.userDevice.dto.response.UserDeviceResponseDTO;
import com.inventariosips.userDevice.mapper.IMapperUserDevice;
import com.inventariosips.userDevice.model.UserDeviceEntity;
import com.inventariosips.userDevice.service.IUserDeviceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
    @RequestMapping("usersDevices")
@RequiredArgsConstructor
public class UserDeviceController {

    private final IUserDeviceService userDeviceService;
    private final IMapperUserDevice mapperUserDevice;

    @GetMapping
    public ResponseEntity<List<UserDeviceResponseDTO>> findAllUserDevices() throws Exception {
        List<UserDeviceEntity> lst = userDeviceService.findAllUserDevice().stream().toList();
        return ResponseEntity.ok(mapperUserDevice.lstUserDeviceEntityToLstUserDeviceResponseDTO(lst));
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<UserDeviceResponseDTO>> findAllUserDevicesPageable(Pageable pageable) throws Exception {
        // 1. Obtener la página de entidades del servicio
        Page<UserDeviceEntity> userPage = userDeviceService.findAllUserDevice(pageable);

        // 2. Convertir la lista de entidades (content) a DTOs
        List<UserDeviceResponseDTO> dtoList = userPage.getContent().stream()
                .map(mapperUserDevice::UserDeviceEntityToUserDeviceResponseDTO)
                .collect(Collectors.toList());

        // 3. Reconstruir la respuesta Page usando los metadatos de la página original
        Page<UserDeviceResponseDTO> dtoPage = new PageImpl<>(
                dtoList,
                pageable,
                userPage.getTotalElements()
        );

        return ResponseEntity.ok(dtoPage);
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
    public ResponseEntity<Void> deleteUserDevice(@PathVariable("id") Integer id, @RequestBody UserDeviceEntity userDevice) throws Exception {
        userDeviceService.deleteUserDevice(userDevice, id);

        return ResponseEntity.noContent().build();
    }

}
