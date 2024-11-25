package com.example.usuario_service.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.usuario_service.model.dto.*;
import com.example.usuario_service.model.entity.Usuario;
import com.example.usuario_service.service.UsuarioService;
import com.example.usuario_service.repository.UsuarioRepository;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {


    @Autowired
    private UsuarioRepository usuarioRepository;


    //Buscar todos los usuarios
    @Override

    public List<UsuarioResponse> getAllUsuarios(){
        return usuarioRepository.getAllUsuarios();
    }


    //Buscar usuario por id
    @Override
    public UsuarioResponse getUsuarioById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuario con id " + id + " no encontrado"));
        return new UsuarioResponse(usuario.getId(), usuario.getNombre(), usuario.getApellido(), usuario.getTelefono(), usuario.getEmail(), usuario.getPassword(), usuario.getRol());
    }


    //Agregar un usuario a la BD
    @Override
    public ResponseEntity<String> addUsuario(UsuarioRequest usuariodto) {
    // Verificar si el usuario ya existe
    Optional<Usuario> existingUser = usuarioRepository.findByEmail(usuariodto.getEmail());
    if (existingUser.isPresent()) {
        // Si el usuario ya existe, lanzar una excepción con un código de estado 409 (Conflict)
        return ResponseEntity.status(HttpStatus.CONFLICT).body("El usuario ya existe.");
    }
    Usuario us = new Usuario(usuariodto.getNombre(), usuariodto.getApellido(), 
                              usuariodto.getTelefono(), usuariodto.getEmail(), 
                              usuariodto.getPassword());
    usuarioRepository.save(us);
    return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado exitosamente.");
    }



    //Actualizar un usuario
    @Override
    public ResponseEntity<String> putUsuario(Long id, UsuarioUpdateRequest usuariodto){
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if(usuario != null){
            usuario.setRol(usuariodto.getRol());
            usuarioRepository.save(usuario);
            return ResponseEntity.status(HttpStatus.OK).body("Usuario actualizado exitosamente.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario no existe.");
    }

    //Eliminar un usuario
    public ResponseEntity<String> deleteUsuario(Long id){
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if(usuario != null){
            usuarioRepository.delete(usuario);
            return ResponseEntity.status(HttpStatus.OK).body("Usuario eliminado exitosamente.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario no existe.");
        
    }


    @Override
    public Optional<Usuario> login(String email, String password) {
        // Buscar usuario por email
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);

        // Verificar si existe y si el password coincide
        return usuarioOpt.filter(usuario -> usuario.getPassword().equals(password));
    }

}