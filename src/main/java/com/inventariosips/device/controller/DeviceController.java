package com.inventariosips.device.controller;

import com.inventariosips.device.dto.request.DeviceRequestDTO;
import com.inventariosips.device.dto.response.DeviceResponseDTO;
import com.inventariosips.device.mapper.IMapperDevice;
import com.inventariosips.device.model.DeviceEntity;
import com.inventariosips.device.service.IDeviceService;
import com.inventariosips.user.dto.response.UserResponseDTO;
import com.inventariosips.user.model.UserEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("devices")
@RequiredArgsConstructor
public class DeviceController {

    private final IDeviceService deviceService;
    private final IMapperDevice mapperDevice;

    @GetMapping
    public ResponseEntity<List<DeviceResponseDTO>> findAllDevices() throws Exception {
        List<DeviceEntity> lst = deviceService.findAllDevice().stream().toList();
        return ResponseEntity.ok(mapperDevice.lstDeviceEntityToLstDeviceResponseDTO(lst));
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<DeviceResponseDTO>> findAllDevicesPageable(
            Pageable pageable,
            @RequestParam(name = "filter", required = false, defaultValue = "") String filter) throws Exception {
        // 1. Obtener la página de entidades del servicio
        Page<DeviceEntity> userPage = deviceService.findAllDevice(pageable, filter);

        // 2. Convertir la lista de entidades (content) a DTOs
        List<DeviceResponseDTO> dtoList = userPage.getContent().stream()
                .map(mapperDevice::DeviceEntityToDeviceResponseDTO)
                .collect(Collectors.toList());

        // 3. Reconstruir la respuesta Page usando los metadatos de la página original
        Page<DeviceResponseDTO> dtoPage = new PageImpl<>(
                dtoList,
                pageable,
                userPage.getTotalElements()
        );

        return ResponseEntity.ok(dtoPage);
    }

    @GetMapping("{id}")
    public ResponseEntity<DeviceResponseDTO> findByIdDevice(@PathVariable("id") Integer id) throws Exception {
        DeviceResponseDTO dto = mapperDevice.DeviceEntityToDeviceResponseDTO(deviceService.findByIdDevice(id));

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<DeviceEntity> saveDevice(@Valid @RequestBody DeviceRequestDTO DeviceRequestDTO) throws Exception{
        DeviceEntity deviceEntity = deviceService.saveDevice(mapperDevice.DeviceRequestDTOToDeviceEntity(DeviceRequestDTO));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(deviceEntity.getIdDevice())
                .toUri();

        return ResponseEntity.created(location).body(deviceEntity);
    }

    @PutMapping("{id}")
    public ResponseEntity<DeviceEntity> updateDevice(@Valid @RequestBody DeviceRequestDTO DeviceRequestDTO, @PathVariable("id") Integer id) throws Exception {
        DeviceRequestDTO.setIdDevice(id);
        DeviceEntity DeviceEntity = deviceService.updateDevice(mapperDevice.DeviceRequestDTOToDeviceEntity(DeviceRequestDTO), id);

        return ResponseEntity.ok(DeviceEntity);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable("id") Integer id) throws Exception {
        deviceService.deleteDevice(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nameUser")
    public ResponseEntity<String> getNameUserByNameDevice(@RequestParam("deviceName") String deviceName) {
        String userName = deviceService.getNameUserByNameDevice(deviceName);
        return ResponseEntity.ok(userName);
    }

    @GetMapping("/nameUserLoan")
    public ResponseEntity<String> getNameUserByNameDeviceLoan(@RequestParam("deviceName") String deviceName) {
        String userName = deviceService.getNameUserByNameDeviceLoan(deviceName);
        return ResponseEntity.ok(userName);
    }

    @GetMapping("/availables")
    public ResponseEntity<List<DeviceEntity>> findAvailableDevices() {
        return ResponseEntity.ok(deviceService.findAvailableDevices());
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadPdf(
            @RequestParam("deviceId") Integer deviceId,
            @RequestParam("file") MultipartFile file) {
        try{
            String url = deviceService.uploadPdf(file, deviceId);
            return ResponseEntity.ok(url);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al subir archivo: " + e.getMessage());
        }

    }


}
