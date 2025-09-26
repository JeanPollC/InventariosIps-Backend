package com.inventariosips.brand.service.impl;

import com.inventariosips.exception.ModelNotFoundException;
import com.inventariosips.brand.model.BrandEntity;
import com.inventariosips.brand.repo.IBrandRepo;
import com.inventariosips.brand.service.IBrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements IBrandService {

    private final IBrandRepo brandRepo;

    @Override
    public BrandEntity saveBrand(BrandEntity brandEntity) {
        return brandRepo.save(brandEntity);
    }

    @Override
    public BrandEntity updateBrand(BrandEntity brandEntity, Integer id) {
        brandRepo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
        return brandRepo.save(brandEntity);
    }

    @Override
    public List<BrandEntity> getAllBrand() {
        return brandRepo.findAll();
    }

    @Override
    public BrandEntity findByIDBrand(Integer id) {
        return brandRepo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
    }

    @Override
    public void deleteBrand(Integer id) {
        brandRepo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
        brandRepo.deleteById(id);
    }
}
