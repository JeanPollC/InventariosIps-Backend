package com.inventariosips.device.controller;

import com.inventariosips.device.dto.request.DeviceRequestDTO;
import com.inventariosips.device.dto.response.DeviceResponseDTO;
import com.inventariosips.device.mapper.IMapperDevice;
import com.inventariosips.device.model.DeviceEntity;
import com.inventariosips.device.service.IDeviceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("devices")
@RequiredArgsConstructor
public class DeviceController {

    private final IDeviceService DeviceService;
    private final IMapperDevice mapperDevice;

    @GetMapping
    public ResponseEntity<List<DeviceResponseDTO>> findAllDevices() throws Exception {
        List<DeviceEntity> lst = DeviceService.findAllDevice().stream().toList();
        return ResponseEntity.ok(mapperDevice.lstDeviceEntityToLstDeviceResponseDTO(lst));
    }

    @GetMapping("{id}")
    public ResponseEntity<DeviceResponseDTO> findByIdDevice(@PathVariable("id") Integer id) throws Exception {
        DeviceResponseDTO dto = mapperDevice.DeviceEntityToDeviceResponseDTO(DeviceService.findByIdDevice(id));

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<DeviceEntity> saveDevice(@Valid @RequestBody DeviceRequestDTO DeviceRequestDTO) throws Exception{
        DeviceEntity DeviceEntity = DeviceService.saveDevice(mapperDevice.DeviceRequestDTOToDeviceEntity(DeviceRequestDTO));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(DeviceEntity.getIdDevice()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<DeviceEntity> updateDevice(@Valid @RequestBody DeviceRequestDTO DeviceRequestDTO, @PathVariable("id") Integer id) throws Exception {
        DeviceRequestDTO.setIdDevice(id);
        DeviceEntity DeviceEntity = DeviceService.updateDevice(mapperDevice.DeviceRequestDTOToDeviceEntity(DeviceRequestDTO), id);

        return ResponseEntity.ok(DeviceEntity);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable("id") Integer id) throws Exception {
        DeviceService.deleteDevice(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nameUser")
    public ResponseEntity<String> getNameUserByNameDevice(@RequestParam("deviceName") String deviceName) {
        String userName = DeviceService.getNameUserByNameDevice(deviceName);
        return ResponseEntity.ok(userName);
    }

}
