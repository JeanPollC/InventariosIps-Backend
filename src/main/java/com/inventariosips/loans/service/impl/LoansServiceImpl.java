package com.inventariosips.loans.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.inventariosips.exception.ModelNotFoundException;
import com.inventariosips.loans.model.LoansEntity;
import com.inventariosips.loans.repo.ILoansRepo;
import com.inventariosips.loans.service.ILoansService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LoansServiceImpl implements ILoansService {

    private final ILoansRepo loansRepo;
    private final Cloudinary cloudinary;

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

    @Override
    public String uploadPdf(MultipartFile file, Integer loanId) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(),
                ObjectUtils.asMap(
                        "resource_type", "auto", // importante para PDF
                        "folder", "prestamos_documentos/" + loanId // opcional
                ));
        String url = uploadResult.get("secure_url").toString();

        LoansEntity loan = loansRepo.findById(loanId)
                .orElseThrow( () -> new RuntimeException("Prestamo no encontrado"));
        loan.setLoanDocument(url);
        loansRepo.save(loan);

        return url;
    }
}
