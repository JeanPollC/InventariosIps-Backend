package com.inventariosips.loans.service;

import com.inventariosips.loans.model.LoansEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ILoansService {

    LoansEntity saveLoans(LoansEntity loansEntity);
    LoansEntity updateLoans(LoansEntity loansEntity, Integer id);
    List<LoansEntity> findAllLoans();
    Page<LoansEntity> findAllLoans(Pageable pageable);
    LoansEntity findByIdLoans(Integer id);
    void deleteLoans(LoansEntity loan, Integer id);

    String uploadPdf(MultipartFile file, Integer loanId) throws IOException;

    LoansEntity closeLoan(Integer idLoan, LocalDateTime endDateLoan);
}

