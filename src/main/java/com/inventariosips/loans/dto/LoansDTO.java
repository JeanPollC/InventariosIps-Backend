package com.inventariosips.loans.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoansDTO {

    private Integer idLoans;

    @NotNull
    private LocalDateTime startDateLoan;

    @NotNull
    private LocalDateTime endDateLoan;

    @NotNull
    private Integer idUser;

    @NotNull
    private Integer idDevice;

    private byte[] loanDocument;

}
