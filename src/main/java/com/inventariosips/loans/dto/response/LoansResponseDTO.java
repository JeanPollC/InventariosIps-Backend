package com.inventariosips.loans.dto.response;

import com.inventariosips.device.model.DeviceEntity;
import com.inventariosips.user.model.UserEntity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoansResponseDTO {

    private Integer idLoans;

    @NotNull
    private UserEntity user;

    @NotNull
    private DeviceEntity device;

    @NotNull
    private LocalDateTime startDateLoan;

    @NotNull
    private LocalDateTime endDateLoan;

    private byte[] loanDocument;

}