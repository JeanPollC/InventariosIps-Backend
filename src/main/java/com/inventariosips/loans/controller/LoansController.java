package com.inventariosips.loans.controller;

import com.inventariosips.loans.dto.request.LoansRequestDTO;
import com.inventariosips.loans.dto.response.LoansResponseDTO;
import com.inventariosips.loans.mapper.IMapperLoans;
import com.inventariosips.loans.model.LoansEntity;
import com.inventariosips.loans.service.ILoansService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("loans")
@RequiredArgsConstructor
public class LoansController {

    private final ILoansService loansService;
    private final IMapperLoans mapperLoans;

    @GetMapping
    public ResponseEntity<List<LoansResponseDTO>> findAllLoanssTypes() throws Exception {
        List<LoansEntity> lst = loansService.findAllLoans().stream().toList();
        return ResponseEntity.ok(mapperLoans.lstLoansEntityToLstLoansResponseDTO(lst));
    }

    @GetMapping("{id}")
    public ResponseEntity<LoansResponseDTO> findByIdLoans(@PathVariable("id") Integer id) throws Exception {
        LoansResponseDTO dto = mapperLoans.LoansEntityToLoansResponseDTO(loansService.findByIdLoans(id));

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<LoansEntity> saveLoans(@Valid @RequestBody LoansRequestDTO loansDTO) throws Exception{
        LoansEntity loansEntity = loansService.saveLoans(mapperLoans.loansDTOToLoansEntity(loansDTO));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(loansEntity.getIdLoans()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<LoansEntity> updateLoans(@Valid @RequestBody LoansRequestDTO loansDTO, @PathVariable("id") Integer id) throws Exception {
        loansDTO.setIdLoans(id);
        LoansEntity loansEntity = loansService.updateLoans(mapperLoans.loansDTOToLoansEntity(loansDTO), id);

        return ResponseEntity.ok(loansEntity);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteLoans(@PathVariable("id") Integer id) throws Exception {
        loansService.deleteLoans(id);

        return ResponseEntity.noContent().build();
    }

}
