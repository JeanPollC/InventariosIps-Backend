package com.inventariosips.loans.service;

import com.inventariosips.loans.model.LoansEntity;

import java.util.List;

public interface ILoansService {

    LoansEntity saveLoans(LoansEntity loansEntity);
    LoansEntity updateLoans(LoansEntity loansEntity, Integer id);
    List<LoansEntity> findAllLoans();
    LoansEntity findByIdLoans(Integer id);
    void deleteLoans(Integer id);
}
