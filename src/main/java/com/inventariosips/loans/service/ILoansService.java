package com.inventariosips.loans.service;

import com.inventariosips.loans.model.LoansEntity;
import com.inventariosips.userDevice.model.UserDeviceEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ILoansService {

    LoansEntity saveLoans(LoansEntity loansEntity);
    LoansEntity updateLoans(LoansEntity loansEntity, Integer id);
    List<LoansEntity> findAllLoans();
    LoansEntity findByIdLoans(Integer id);
    void deleteLoans(Integer id);

    String uploadPdf(MultipartFile file, Integer loanId) throws IOException;

    LoansEntity createLoan(LoansEntity loan);

    LoansEntity closeLoan(Integer idLoan, LocalDateTime endDateLoan);
}
