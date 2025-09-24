package com.inventariosips.brand.service;

import com.inventariosips.brand.model.BrandEntity;

import java.util.List;

public interface IBrandService {
    BrandEntity saveBrand(BrandEntity brandEntity);
    BrandEntity updateBrand(BrandEntity brandEntity, Integer id);
    List<BrandEntity> getAllBrand();
    BrandEntity findByIDBrand(Integer id);
    void deleteBrand(Integer id);

}
