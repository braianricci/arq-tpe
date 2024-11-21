package com.example.usuario_service.model.dto;

import com.example.usuario_service.model.entity.Usuario.Rol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioUpdateRequest {
    
    private Rol rol;
}
