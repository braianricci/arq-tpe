package com.example.viaje_service.service;

import java.util.List;
import com.example.viaje_service.model.dto.*;
import org.springframework.stereotype.Service;

@Service
public interface ViajeService {

    // Métodos CRUD
    List<ViajeResponse> getAllViajes();
    ViajeResponse getViajeById(Long id);
    ViajeResponse addViaje(ViajeCreateRequest viaje);
    void updateViaje(Long id, ViajeUpdateRequest viaje);
    void deleteViaje(Long id);

}
