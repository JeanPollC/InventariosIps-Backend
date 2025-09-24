package com.inventariosips.warranty.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WarrantyDTO{

        private Integer idWarranty;

        @NotNull
        @Size(min = 5)
        private String statusWarranty;

        @NotNull
        private LocalDate startDate;

        @NotNull
        private LocalDate endDate;
}
