package com.inventariosips.warranty.service.impl;

import com.inventariosips.exception.ModelNotFoundException;
import com.inventariosips.warranty.model.WarrantyEntity;
import com.inventariosips.warranty.repo.IWarrantyRepo;
import com.inventariosips.warranty.service.IWarrantyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WarrantyServiceImpl implements IWarrantyService {

    private final IWarrantyRepo warrantyRepo;

    @Override
    public WarrantyEntity saveWarranty(WarrantyEntity warrantyEntity) {
        return warrantyRepo.save(warrantyEntity);
    }

    @Override
    public WarrantyEntity updateWarranty(WarrantyEntity warrantyEntity, Integer id) {
        warrantyRepo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
        return warrantyRepo.save(warrantyEntity);
    }

    @Override
    public List<WarrantyEntity> getAllWarranty() {
        return warrantyRepo.findAll();
    }

    @Override
    public WarrantyEntity findByIDWarranty(Integer id) {
        return warrantyRepo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
    }

    @Override
    public void deleteWarranty(Integer id) {
        warrantyRepo.deleteById(id);
    }
}
