package com.inventariosips.warranty.controller;

import com.inventariosips.warranty.dto.WarrantyDTO;
import com.inventariosips.warranty.mapper.IMapperWarranty;
import com.inventariosips.warranty.model.WarrantyEntity;
import com.inventariosips.warranty.service.IWarrantyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/warranties")
@RequiredArgsConstructor
public class WarrantyController {

    private final IWarrantyService service;
    private final IMapperWarranty mapperWarranty;

    @GetMapping
    public ResponseEntity<List<WarrantyDTO>> getAllWarranty() throws Exception{
        List<WarrantyEntity> list = service.getAllWarranty().stream().toList();

        return ResponseEntity.ok(mapperWarranty.lstWarrantyEntityToListWarrantyDTO(list));
    }

    @GetMapping("{id}")
    public ResponseEntity<WarrantyDTO> findByIdWarranty(@PathVariable("id") Integer id) throws Exception{

        WarrantyEntity warrantyEntity = service.findByIDWarranty(id);
        return ResponseEntity.ok(mapperWarranty.warrantyEntityToWarrantyDTO(warrantyEntity));
    }

    @PostMapping
    public ResponseEntity<WarrantyEntity> saveWarranty(@Valid @RequestBody WarrantyDTO warrantyDTO) throws Exception {
        WarrantyEntity warrantyEntity = service.saveWarranty(mapperWarranty.warrantyDTOToWarrantyEntity(warrantyDTO));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(warrantyEntity.getIdWarranty()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<WarrantyEntity> updateWarranty(@Valid @RequestBody WarrantyDTO warrantyDTO, @PathVariable("id") Integer id) throws Exception {
        warrantyDTO.setIdWarranty(id);
        WarrantyEntity warrantyEntity = service.updateWarranty(mapperWarranty.warrantyDTOToWarrantyEntity(warrantyDTO), id);
        return ResponseEntity.ok(warrantyEntity);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteWarranty(@PathVariable("id") Integer id) throws Exception {
        service.deleteWarranty(id);

        return ResponseEntity.noContent().build();
    }


}
