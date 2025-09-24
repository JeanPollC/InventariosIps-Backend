package com.inventariosips.loans.controller;

import com.inventariosips.loans.dto.LoansDTO;
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
    public ResponseEntity<List<LoansDTO>> findAllLoanssTypes() throws Exception {
        List<LoansEntity> lst = loansService.findAllLoans().stream().toList();
        return ResponseEntity.ok(mapperLoans.lstLoansEntityToLstLoansDTO(lst));
    }

    @GetMapping("{id}")
    public ResponseEntity<LoansDTO> findByIdLoans(@PathVariable("id") Integer id) throws Exception {
        LoansDTO dto = mapperLoans.loansEntityToLoansDTO(loansService.findByIdLoans(id));

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<LoansEntity> saveLoans(@Valid @RequestBody LoansDTO loansDTO) throws Exception{
        LoansEntity loansEntity = loansService.saveLoans(mapperLoans.loansDTOToLoansEntity(loansDTO));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(loansEntity.getIdLoans()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<LoansEntity> updateLoans(@Valid @RequestBody LoansDTO loansDTO, @PathVariable("id") Integer id) throws Exception {
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
