package com.example.usuario_service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.usuario_service.model.dto.UsuarioResponse;
import com.example.usuario_service.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    @Query("SELECT new com.example.usuario_service.model.dto.UsuarioResponse(u.id, u.nombre, u.apellido, u.email, u.telefono) FROM Usuario u")
    List<UsuarioResponse> getAllUsuarios();

    //Metodo usado para validar si el usuario ya existe
    Optional<Usuario> findByEmail(String email);



}
