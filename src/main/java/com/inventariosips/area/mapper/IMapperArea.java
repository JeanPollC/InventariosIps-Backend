package com.inventariosips.area.mapper;

import com.inventariosips.area.dto.AreaDTO;
import com.inventariosips.area.model.AreaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMapperArea {


    AreaEntity areaDTOToAreaEntity(AreaDTO areaDTO);

    AreaDTO areaEntityToAreaDTO(AreaEntity areaEntity);

    List<AreaDTO> lstAreaEntityToListAreaDTO(List<AreaEntity> lstAreaEntity);

}
