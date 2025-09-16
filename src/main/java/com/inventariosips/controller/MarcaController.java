package com.inventariosips.controller;

import com.inventariosips.model.Marca;
import com.inventariosips.service.IMarcaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marca")
@RequiredArgsConstructor
public class MarcaController {

    private final IMarcaService service;

    @GetMapping
    public ResponseEntity<List<Marca>> findAll() throws Exception{
        List<Marca> list = service.findAll().stream().toList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("{id}")
    public ResponseEntity<Marca> findById(@PathVariable("id") Integer id) throws Exception{
        Marca obj = service.findById(id);
        return ResponseEntity.ok(obj);
    }
}
