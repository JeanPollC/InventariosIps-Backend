package com.inventariosips.area.service;

import com.inventariosips.area.model.AreaEntity;

import java.util.List;

public interface IAreaService {
    AreaEntity saveArea(AreaEntity areaEntity);
    AreaEntity updateArea(AreaEntity areaEntity, Integer id);
    List<AreaEntity> getAllArea();
    AreaEntity findByIDArea(Integer id);
    void deleteArea(Integer id);

}
