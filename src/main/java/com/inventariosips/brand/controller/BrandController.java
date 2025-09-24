package com.inventariosips.brand.controller;

import com.inventariosips.brand.dto.BrandDTO;
import com.inventariosips.brand.mapper.IMapperBrand;
import com.inventariosips.brand.model.BrandEntity;
import com.inventariosips.brand.service.IBrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/brands")
@RequiredArgsConstructor
public class BrandController {

    private final IBrandService service;
    private final IMapperBrand mapperBrand;

    @GetMapping
    public ResponseEntity<List<BrandDTO>> getAllBrand() throws Exception{
        List<BrandEntity> list = service.getAllBrand().stream().toList();

        return ResponseEntity.ok(mapperBrand.lstBrandEntityToListBrandDTO(list));
    }

    @GetMapping("{id}")
    public ResponseEntity<BrandDTO> findByIdBrand(@PathVariable("id") Integer id) throws Exception{

        BrandEntity brandEntity = service.findByIDBrand(id);
        return ResponseEntity.ok(mapperBrand.brandEntityToBrandDTO(brandEntity));
    }

    @PostMapping
    public ResponseEntity<BrandEntity> saveBrand(@Valid @RequestBody BrandDTO brandDTO) throws Exception {
        BrandEntity brandEntity = service.saveBrand(mapperBrand.brandDTOToBrandEntity(brandDTO));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(brandEntity.getIdBrand()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrandEntity> updateBrand(@Valid @RequestBody BrandDTO brandDTO, @PathVariable("id") Integer id) throws Exception {
        brandDTO.setIdBrand(id);
        BrandEntity brandEntity = service.updateBrand(mapperBrand.brandDTOToBrandEntity(brandDTO), id);
        return ResponseEntity.ok(brandEntity);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable("id") Integer id) throws Exception {
        service.deleteBrand(id);

        return ResponseEntity.noContent().build();
    }


}
