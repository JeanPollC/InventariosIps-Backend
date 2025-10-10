package com.inventariosips.area.service.impl;

import com.inventariosips.area.model.AreaEntity;
import com.inventariosips.area.repo.IAreaRepo;
import com.inventariosips.area.service.IAreaService;
import com.inventariosips.exception.ModelNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AreaServiceImpl implements IAreaService {

    private final IAreaRepo areaRepo;

    @Override
    public AreaEntity saveArea(AreaEntity areaEntity) {
        return areaRepo.save(areaEntity);
    }

    @Override
    public AreaEntity updateArea(AreaEntity areaEntity, Integer id) {
        areaRepo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
        return areaRepo.save(areaEntity);
    }

    @Override
    public List<AreaEntity> getAllArea() {
        return areaRepo.findAll();
    }

    @Override
    public AreaEntity findByIDArea(Integer id) {
        return areaRepo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
    }

    @Override
    public void deleteArea(Integer id) {
        areaRepo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
        areaRepo.deleteById(id);
    }
}
