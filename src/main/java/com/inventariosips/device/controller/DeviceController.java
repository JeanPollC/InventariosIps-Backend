package com.inventariosips.device.controller;

import com.inventariosips.device.dto.DeviceDTO;
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
    public ResponseEntity<List<DeviceDTO>> findAllDevicesTypes() throws Exception {
        List<DeviceEntity> lst = DeviceService.findAllDevice().stream().toList();
        return ResponseEntity.ok(mapperDevice.lstDeviceEntityToLstDeviceDTO(lst));
    }

    @GetMapping("{id}")
    public ResponseEntity<DeviceDTO> findByIdDevice(@PathVariable("id") Integer id) throws Exception {
        DeviceDTO dto = mapperDevice.DeviceEntityToDeviceDTO(DeviceService.findByIdDevice(id));

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<DeviceEntity> saveDevice(@Valid @RequestBody DeviceDTO DeviceDTO) throws Exception{
        DeviceEntity DeviceEntity = DeviceService.saveDevice(mapperDevice.DeviceDTOToDeviceEntity(DeviceDTO));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(DeviceEntity.getIdDevice()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<DeviceEntity> updateDevice(@Valid @RequestBody DeviceDTO DeviceDTO, @PathVariable("id") Integer id) throws Exception {
        DeviceDTO.setIdDevice(id);
        DeviceEntity DeviceEntity = DeviceService.updateDevice(mapperDevice.DeviceDTOToDeviceEntity(DeviceDTO), id);

        return ResponseEntity.ok(DeviceEntity);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable("id") Integer id) throws Exception {
        DeviceService.deleteDevice(id);

        return ResponseEntity.noContent().build();
    }

}
