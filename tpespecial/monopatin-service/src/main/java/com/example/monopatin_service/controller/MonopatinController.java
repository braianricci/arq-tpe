package com.example.monopatin_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


import com.example.monopatin_service.model.dto.*;

import com.example.monopatin_service.service.MonopatinService;

@RestController
@RequestMapping("/monopatin")
public class MonopatinController {

    @Autowired
    private MonopatinService monopatinService;

    @GetMapping
    public List<MonopatinResponse> getAllMonopatines() {
        return monopatinService.getAllMonopatines();
    }

    @GetMapping("/{id}")
    public MonopatinResponse getMonopatinById(@PathVariable String id) {
        return monopatinService.getMonopatinById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addMonopatin(@RequestBody MonopatinCreateRequest monopatin) {
        monopatinService.addMonopatin(monopatin);
    }

    @PutMapping("/put/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putMonopatin(@PathVariable String id, @RequestBody MonopatinUpdateRequest monopatin) {
        monopatinService.putMonopatin(id, monopatin);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMonopatin(@PathVariable String id) {
        monopatinService.deleteMonopatin(id);
    }
}
