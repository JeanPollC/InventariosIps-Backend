package com.inventariosips.warranty.mapper;

import com.inventariosips.warranty.dto.WarrantyDTO;
import com.inventariosips.warranty.model.WarrantyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMapperWarranty {

    @Mapping(source = "statusWarranty", target = "status")
    WarrantyEntity warrantyDTOToWarrantyEntity(WarrantyDTO warrantyDTO);

    @Mapping(source = "status", target = "statusWarranty")
    WarrantyDTO warrantyEntityToWarrantyDTO(WarrantyEntity warrantyEntity);

    List<WarrantyDTO> lstWarrantyEntityToListWarrantyDTO(List<WarrantyEntity> lstWarrantyEntity);

}
