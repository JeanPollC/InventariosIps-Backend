package com.inventariosips.statusDevice.controller;

import com.inventariosips.statusDevice.dto.StatusDeviceDTO;
import com.inventariosips.statusDevice.mapper.IMapperStatusDevice;
import com.inventariosips.statusDevice.model.StatusDeviceEntity;
import com.inventariosips.statusDevice.service.IStatusDeviceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/statusDevices")
@RequiredArgsConstructor
public class StatusDeviceController {

    private final IStatusDeviceService service;
    private final IMapperStatusDevice mapperStatusDevice;

    @GetMapping
    public ResponseEntity<List<StatusDeviceDTO>> getAllStatusDevice() throws Exception{
        List<StatusDeviceEntity> list = service.getAllStatusDevice().stream().toList();

        return ResponseEntity.ok(mapperStatusDevice.lstStatusDeviceEntityToListStatusDeviceDTO(list));
    }

    @GetMapping("{id}")
    public ResponseEntity<StatusDeviceDTO> findByIdStatusDevice(@PathVariable("id") Integer id) throws Exception{

        StatusDeviceEntity statusDeviceEntity = service.findByIDStatusDevice(id);
        return ResponseEntity.ok(mapperStatusDevice.statusDeviceEntityToStatusDeviceDTO(statusDeviceEntity));
    }

    @PostMapping
    public ResponseEntity<StatusDeviceEntity> saveStatusDevice(@Valid @RequestBody StatusDeviceDTO statusDeviceDTO) throws Exception {
        StatusDeviceEntity statusDeviceEntity = service.saveStatusDevice(mapperStatusDevice.statusDeviceDTOToStatusDeviceEntity(statusDeviceDTO));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(statusDeviceEntity.getIdStatusDevice()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<StatusDeviceEntity> updateStatusDevice(@Valid @RequestBody StatusDeviceDTO statusDeviceDTO, @PathVariable("id") Integer id) throws Exception {
        statusDeviceDTO.setIdStatusDevice(id);
        StatusDeviceEntity statusDeviceEntity = service.updateStatusDevice(mapperStatusDevice.statusDeviceDTOToStatusDeviceEntity(statusDeviceDTO), id);
        return ResponseEntity.ok(statusDeviceEntity);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStatusDevice(@PathVariable("id") Integer id) throws Exception {
        service.deleteStatusDevice(id);

        return ResponseEntity.noContent().build();
    }


}
