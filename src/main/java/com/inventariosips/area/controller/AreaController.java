package com.inventariosips.area.controller;

import com.inventariosips.area.dto.AreaDTO;
import com.inventariosips.area.mapper.IMapperArea;
import com.inventariosips.area.model.AreaEntity;
import com.inventariosips.area.service.IAreaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/areas")
@RequiredArgsConstructor
public class AreaController {

    private final IAreaService service;
    private final IMapperArea mapperArea;

    @GetMapping
    public ResponseEntity<List<AreaDTO>> getAllArea() throws Exception{
        List<AreaEntity> list = service.getAllArea().stream().toList();

        return ResponseEntity.ok(mapperArea.lstAreaEntityToListAreaDTO(list));
    }

    @GetMapping("{id}")
    public ResponseEntity<AreaDTO> findByIdArea(@PathVariable("id") Integer id) throws Exception{

        AreaEntity areaEntity = service.findByIDArea(id);
        return ResponseEntity.ok(mapperArea.areaEntityToAreaDTO(areaEntity));
    }

    @PostMapping
    public ResponseEntity<AreaEntity> saveArea(@Valid @RequestBody AreaDTO areaDTO) throws Exception {
        AreaEntity areaEntity = service.saveArea(mapperArea.areaDTOToAreaEntity(areaDTO));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(areaEntity.getIdArea()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AreaEntity> updateArea(@Valid @RequestBody AreaDTO areaDTO, @PathVariable("id") Integer id) throws Exception {
        areaDTO.setIdArea(id);
        AreaEntity areaEntity = service.updateArea(mapperArea.areaDTOToAreaEntity(areaDTO), id);
        return ResponseEntity.ok(areaEntity);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteArea(@PathVariable("id") Integer id) throws Exception {
        service.deleteArea(id);

        return ResponseEntity.noContent().build();
    }


}
