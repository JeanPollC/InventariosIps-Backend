package com.inventariosips.service.impl;

import com.inventariosips.model.Marca;
import com.inventariosips.repo.IGenericRepo;
import com.inventariosips.repo.IMarcaRepo;
import com.inventariosips.service.IMarcaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MarcaImpl extends CRUDImpl<Marca, Integer> implements IMarcaService {

    private final IMarcaRepo repo;

    @Override
    protected IGenericRepo<Marca, Integer> getRepo() {
        return repo;
    }
}
