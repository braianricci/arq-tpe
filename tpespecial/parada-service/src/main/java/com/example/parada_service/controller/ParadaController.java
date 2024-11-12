package com.example.parada_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import com.example.parada_service.model.dto.*;
import com.example.parada_service.service.ParadaService;

@RestController
@RequestMapping("/paradas")
public class ParadaController {

    @Autowired
    private ParadaService paradaService;

    @GetMapping
    public List<ParadaResponse> getAllParadas() {
        return paradaService.getAllParadas();
    }

    @GetMapping("/{id}")
    public ParadaResponse getParadaById(@PathVariable String id) {
        return paradaService.getParadaById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addParada(@RequestBody ParadaRequest parada) {
        paradaService.addParada(parada);
    }

    @PutMapping("/put/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateParada(@PathVariable String id, @RequestBody ParadaRequest parada) {
        paradaService.putParada(id, parada);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteParada(@PathVariable String id) {
        paradaService.deleteParada(id);
    }
}