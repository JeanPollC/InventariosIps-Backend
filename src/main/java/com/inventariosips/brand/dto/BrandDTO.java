package com.inventariosips.brand.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BrandDTO{

        private Integer idBrand;

        @NotNull
        @Size(min = 2)
        private String descripcion;
}
