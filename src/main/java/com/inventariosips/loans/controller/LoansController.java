package com.inventariosips.loans.controller;

import com.inventariosips.loans.dto.request.LoansRequestDTO;
import com.inventariosips.loans.dto.response.LoansResponseDTO;
import com.inventariosips.loans.mapper.IMapperLoans;
import com.inventariosips.loans.model.LoansEntity;
import com.inventariosips.loans.service.ILoansService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("loans")
@RequiredArgsConstructor
public class LoansController {

    private final ILoansService loansService;
    private final IMapperLoans mapperLoans;

    @GetMapping
    public ResponseEntity<List<LoansResponseDTO>> findAllLoans() throws Exception {
        List<LoansEntity> lst = loansService.findAllLoans().stream().toList();
        return ResponseEntity.ok(mapperLoans.lstLoansEntityToLstLoansResponseDTO(lst));
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<LoansResponseDTO>> findAllLoanssPageable(Pageable pageable) throws Exception {
        // 1. Obtener la página de entidades del servicio
        Page<LoansEntity> loansPage = loansService.findAllLoans(pageable);

        // 2. Convertir la lista de entidades (content) a DTOs
        List<LoansResponseDTO> dtoList = loansPage.getContent().stream()
                .map(mapperLoans::LoansEntityToLoansResponseDTO)
                .collect(Collectors.toList());

        // 3. Reconstruir la respuesta Page usando los metadatos de la página original
        Page<LoansResponseDTO> dtoPage = new PageImpl<>(
                dtoList,
                pageable,
                loansPage.getTotalElements()
        );

        return ResponseEntity.ok(dtoPage);
    }

    @GetMapping("{id}")
    public ResponseEntity<LoansResponseDTO> findByIdLoans(@PathVariable("id") Integer id) throws Exception {
        LoansResponseDTO dto = mapperLoans.LoansEntityToLoansResponseDTO(loansService.findByIdLoans(id));

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<LoansEntity> saveLoans(@Valid @RequestBody LoansRequestDTO loansDTO) throws Exception{
        LoansEntity loansEntity = loansService.saveLoans(mapperLoans.loansDTOToLoansEntity(loansDTO));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(loansEntity.getIdLoans())
                .toUri();

        return ResponseEntity.created(location).body(loansEntity);
    }

    @PutMapping("{id}")
    public ResponseEntity<LoansEntity> updateLoans(@Valid @RequestBody LoansRequestDTO loansDTO, @PathVariable("id") Integer id) throws Exception {
        loansDTO.setIdLoans(id);
        LoansEntity loansEntity = loansService.updateLoans(mapperLoans.loansDTOToLoansEntity(loansDTO), id);

        return ResponseEntity.ok(loansEntity);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteLoans(@PathVariable("id") Integer id, @RequestBody LoansEntity loanEntity) throws Exception {
        loansService.deleteLoans(loanEntity, id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadPdf(
            @RequestParam("loanId") Integer loanId,
            @RequestParam("file")   MultipartFile file) {
        try{
            String url = loansService.uploadPdf(file, loanId);
            return ResponseEntity.ok(url);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al subir archivo: " + e.getMessage());
        }

    }

}
