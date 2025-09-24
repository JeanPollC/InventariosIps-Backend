package com.inventariosips.brand.mapper;

import com.inventariosips.brand.dto.BrandDTO;
import com.inventariosips.brand.model.BrandEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMapperBrand {

    BrandEntity brandDTOToBrandEntity(BrandDTO brandDTO);
    BrandDTO brandEntityToBrandDTO(BrandEntity brandEntity);
    List<BrandDTO> lstBrandEntityToListBrandDTO(List<BrandEntity> lstBrandEntity);

}
