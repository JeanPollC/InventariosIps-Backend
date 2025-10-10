package com.inventariosips.area.repo;

import com.inventariosips.area.model.AreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAreaRepo extends JpaRepository<AreaEntity, Integer> {
}
