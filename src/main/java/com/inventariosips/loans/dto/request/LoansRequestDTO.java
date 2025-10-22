package com.inventariosips.loans.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoansRequestDTO {

    private Integer idLoans;

    @NotNull
    private Integer idUser;

    @NotNull
    private Integer idDevice;

    @NotNull
    private LocalDateTime startDateLoan;

    @NotNull
    private LocalDateTime endDateLoan;

    private String loanDocument;

}

