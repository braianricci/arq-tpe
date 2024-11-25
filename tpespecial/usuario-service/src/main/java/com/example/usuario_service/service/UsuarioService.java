package com.example.usuario_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.example.usuario_service.model.dto.UsuarioRequest;
import com.example.usuario_service.model.dto.UsuarioResponse;
import com.example.usuario_service.model.dto.UsuarioUpdateRequest;
import com.example.usuario_service.model.entity.Usuario;


public interface UsuarioService {

    List<UsuarioResponse> getAllUsuarios();
    UsuarioResponse getUsuarioById(Long id);
    ResponseEntity<String> addUsuario(UsuarioRequest usuario);
    ResponseEntity<String> putUsuario(Long id, UsuarioUpdateRequest usuario);
    ResponseEntity<String> deleteUsuario(Long id);
    Optional<Usuario> login(String email, String password);

}