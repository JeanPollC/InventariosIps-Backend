package com.inventariosips.loans.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.inventariosips.device.model.DeviceEntity;
import com.inventariosips.device.service.IDeviceService;
import com.inventariosips.exception.ModelNotFoundException;
import com.inventariosips.loans.model.LoansEntity;
import com.inventariosips.loans.repo.ILoansRepo;
import com.inventariosips.loans.service.ILoansService;
import com.inventariosips.loans.model.LoansEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LoansServiceImpl implements ILoansService {

    private final ILoansRepo loansRepo;
    private final Cloudinary cloudinary;
    private final IDeviceService deviceService;

    @Override
    public LoansEntity saveLoans(LoansEntity loansEntity) {

        DeviceEntity device = deviceService.findByIdDevice(loansEntity.getDevice().getIdDevice());

        if (device == null) {
            throw new ModelNotFoundException("Dispositivo no encontrado con ID: " + loansEntity.getDevice().getIdDevice());
        }

        loansEntity.setDevice(device);
        
        // Aquí decides si es asignación o préstamo, según el tipo
        if (loansEntity.getDevice().getStatusDevice().getNameStatus().equals("Disponible")) {
            LoansEntity saved = loansRepo.save(loansEntity);
            deviceService.updateDeviceStatus(loansEntity.getDevice().getIdDevice(),3); //Prestado
            if (loansEntity.getEndDateLoan() != null && !loansEntity.getEndDateLoan().isAfter(LocalDateTime.now())){
                return closeLoan(saved.getIdLoans(), saved.getEndDateLoan());
            }
            return saved;
        } else {
            throw new IllegalStateException("El dispositivo no está disponible para asignar.");
        }
    }

    @Override
    public LoansEntity updateLoans(LoansEntity loansEntity, Integer id) {
        LoansEntity existing = loansRepo.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));

        if (loansEntity.getStartDateLoan() != null){
            existing.setStartDateLoan(loansEntity.getStartDateLoan());
            deviceService.updateDeviceStatus(loansEntity.getDevice().getIdDevice(), 3);
        }

        if (loansEntity.getEndDateLoan() != null) {
            existing.setEndDateLoan(loansEntity.getEndDateLoan());
            if (!loansEntity.getEndDateLoan().isAfter(LocalDateTime.now())){
                return closeLoan(id, loansEntity.getEndDateLoan());
            }
        }
        return loansRepo.save(loansEntity);
    }

    @Override
    public List<LoansEntity> findAllLoans() {
        return loansRepo.findAll();
    }

    @Override
    public Page<LoansEntity> findAllLoans(Pageable pageable) {
        return loansRepo.findAll(pageable);
    }

    @Override
    public LoansEntity findByIdLoans(Integer id) {
        return loansRepo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
    }

    @Override
    public void deleteLoans(LoansEntity loan, Integer id) {
        loansRepo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
        loansRepo.deleteById(id);
        deviceService.updateDeviceStatus(loan.getDevice().getIdDevice(), 1);
    }

    @Override
    public String uploadPdf(MultipartFile file, Integer loanId) throws IOException {
        // Obtener el nombre original del archivo (incluye .pdf)
        String originalFilename = file.getOriginalFilename();

        // Opcional: quitar espacios o caracteres especiales
        String publicId = originalFilename != null ? originalFilename.replaceAll("\\s+", "_") : "documento.pdf";

        Map uploadResult = cloudinary.uploader().upload(file.getBytes(),
                ObjectUtils.asMap(
                        "resource_type", "raw",
                        "type", "upload",
                        "folder", "prestamos_documentos/" + loanId,
                        "public_id", publicId,  // 👈 Asegura que se guarde con nombre y extensión
                        "use_filename", true,   // 👈 Conserva el nombre del archivo
                        "unique_filename", false // 👈 Evita que Cloudinary lo renombre
                ));
        String url = uploadResult.get("secure_url").toString();

        LoansEntity loan = loansRepo.findById(loanId)
                .orElseThrow( () -> new RuntimeException("Prestamo no encontrado"));
        loan.setLoanDocument(url);
        loansRepo.save(loan);

        return url;
    }

    @Override
    public LoansEntity closeLoan(Integer idLoan, LocalDateTime endDateLoan) {
        LoansEntity loan = loansRepo.findById(idLoan)
                .orElseThrow(()-> new ModelNotFoundException("Prestamo no encontrada"));

        // Si el usuario envía una fecha, úsala. Si no, toma la actual.
        LocalDateTime effectiveEndDate  =
                endDateLoan != null ? endDateLoan : LocalDateTime.now();

        loan.setEndDateLoan(effectiveEndDate );
        loansRepo.save(loan);

        // Si la fecha ya llegó o ya pasó, liberar el dispositivo
        if (!effectiveEndDate.isAfter(LocalDateTime.now())){
            deviceService.updateDeviceStatus(loan.getDevice().getIdDevice(), 1);
        }

        return loan;
    }


}
