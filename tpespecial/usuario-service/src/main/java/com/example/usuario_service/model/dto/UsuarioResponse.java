package com.example.usuario_service.model.dto;

import com.example.usuario_service.model.entity.Usuario.Rol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponse {
    
    private Long id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private String password;
    private Rol rol;

}
