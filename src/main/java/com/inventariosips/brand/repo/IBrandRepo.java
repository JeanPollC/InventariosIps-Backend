package com.inventariosips.brand.repo;

import com.inventariosips.brand.model.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBrandRepo extends JpaRepository<BrandEntity, Integer> {
}
