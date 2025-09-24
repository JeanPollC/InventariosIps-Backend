package com.inventariosips.warranty.service;

import com.inventariosips.warranty.model.WarrantyEntity;

import java.util.List;

public interface IWarrantyService {
    WarrantyEntity saveWarranty(WarrantyEntity warrantyEntity);
    WarrantyEntity updateWarranty(WarrantyEntity warrantyEntity, Integer id);
    List<WarrantyEntity> getAllWarranty();
    WarrantyEntity findByIDWarranty(Integer id);
    void deleteWarranty(Integer id);

}
