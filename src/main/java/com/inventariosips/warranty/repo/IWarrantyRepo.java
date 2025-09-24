package com.inventariosips.warranty.repo;

import com.inventariosips.warranty.model.WarrantyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWarrantyRepo extends JpaRepository<WarrantyEntity, Integer> {
}
