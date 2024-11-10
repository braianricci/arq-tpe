package com.example.monopatin_service.service.impl;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.monopatin_service.model.dto.reportes.ReporteKilometrosDTO;
import com.example.monopatin_service.model.dto.reportes.ReporteTiempoDTO;
import com.example.monopatin_service.repository.MonopatinRepository;
import com.example.monopatin_service.service.ReporteService;

@Service
public class ReporteServiceImpl implements ReporteService {

    @Autowired
    private MonopatinRepository monopatinRepository;

    @Override
    public List<ReporteKilometrosDTO> obtenerUsoPorKilometros() {
        return monopatinRepository.findAll().stream()
                .map(m -> new ReporteKilometrosDTO(m.getId(), m.getModelo(), m.getKilometrajeTotal()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReporteTiempoDTO> obtenerUsoPorTiempoConPausas() {
        return monopatinRepository.findAll().stream()
                .map(m -> new ReporteTiempoDTO(m.getId(), m.getModelo(), m.getTiempoUsoTotal(), m.getTiempoEnPausa()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReporteTiempoDTO> obtenerUsoPorTiempoSinPausas() {
        return monopatinRepository.findAll().stream()
        .map(m -> new ReporteTiempoDTO(m.getId(), m.getModelo(), m.getTiempoUsoTotal() - m.getTiempoEnPausa(), 0))
        .collect(Collectors.toList());
    }
    
}
