package com.example.usuario_service.service;

import java.util.List;

import com.example.usuario_service.model.dto.UsuarioRequest;
import com.example.usuario_service.model.dto.UsuarioResponse;


public interface UsuarioService {

    List<UsuarioResponse> getAllUsuarios();
    UsuarioResponse getUsuarioById(Long id);
    void addUsuario(UsuarioRequest usuario);
    void putUsuario(Long id, UsuarioRequest usuario);
    void deleteUsuario(Long id);

}