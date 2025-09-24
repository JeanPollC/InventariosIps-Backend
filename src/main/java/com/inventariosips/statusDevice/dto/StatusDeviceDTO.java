package com.inventariosips.statusDevice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StatusDeviceDTO{

        private Integer idStatusDevice;

        @NotNull
        @Size(min = 5)
        private String description;
}
