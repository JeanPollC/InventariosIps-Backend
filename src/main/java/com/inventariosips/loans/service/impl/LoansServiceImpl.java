package com.inventariosips.loans.service.impl;

import com.inventariosips.exception.ModelNotFoundException;
import com.inventariosips.loans.model.LoansEntity;
import com.inventariosips.loans.repo.ILoansRepo;
import com.inventariosips.loans.service.ILoansService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoansServiceImpl implements ILoansService {

    private final ILoansRepo loansRepo;

    @Override
    public LoansEntity saveLoans(LoansEntity loansEntity) {
        return loansRepo.save(loansEntity);
    }

    @Override
    public LoansEntity updateLoans(LoansEntity loansEntity, Integer id) {
        loansRepo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
        return loansRepo.save(loansEntity);
    }

    @Override
    public List<LoansEntity> findAllLoans() {
        return loansRepo.findAll();
    }

    @Override
    public LoansEntity findByIdLoans(Integer id) {
        return loansRepo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
    }

    @Override
    public void deleteLoans(Integer id) {
        loansRepo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
        loansRepo.deleteById(id);
    }
}
