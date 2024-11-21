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
    public List<UsuarioResponse> getAllUsuarios(){
        return usuarioRepository.getAllUsuarios();
    }


    //Buscar usuario por id
    public UsuarioResponse getUsuarioById(Long id){
       Usuario usuario = usuarioRepository.findById(id).orElse(null);
       if(usuario !=null){
           return new UsuarioResponse(usuario.getId(), usuario.getNombre(), usuario.getApellido(), usuario.getTelefono(), usuario.getEmail(), usuario.getPassword(), usuario.getRol());
       }
       return null;
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

    // Crear un nuevo usuario
    Usuario us = new Usuario(usuariodto.getNombre(), usuariodto.getApellido(), 
                              usuariodto.getTelefono(), usuariodto.getEmail(), 
                              usuariodto.getPassword());

    // Guardar el nuevo usuario
    usuarioRepository.save(us);

    // Devolver una respuesta con código 201 (Created) y un mensaje de éxito
    return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado exitosamente.");
}



    //Actualizar un usuario
    @Override
    public void putUsuario(Long id, UsuarioUpdateRequest usuariodto){
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Cuenta con id " + id + " no encontrado"));

        usuario.setRol(usuariodto.getRol());
        usuarioRepository.save(usuario);
        
    }

    //Eliminar un usuario
    public void deleteUsuario(Long id){
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if(usuario != null){
            usuarioRepository.delete(usuario);
        }
    }


    @Override
    public Optional<Usuario> login(String email, String password) {
        // Buscar usuario por email
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);

        // Verificar si existe y si el password coincide
        return usuarioOpt.filter(usuario -> usuario.getPassword().equals(password));
    }

}