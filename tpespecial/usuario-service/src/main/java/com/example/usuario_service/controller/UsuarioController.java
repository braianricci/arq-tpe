package com.example.usuario_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.usuario_service.client.MonopatinClient;
import com.example.usuario_service.model.dto.LoginRequest;
import com.example.usuario_service.model.dto.MonopatinesCercanosDTO;
import com.example.usuario_service.model.dto.UsuarioRequest;
import com.example.usuario_service.model.dto.UsuarioResponse;
import com.example.usuario_service.model.dto.UsuarioUpdateRequest;
import com.example.usuario_service.security.JwtUtil;
import com.example.usuario_service.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Usuario Controller", description = "APIs para gestionar usuarios")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private MonopatinClient monopatinClient;


    @Operation(summary = "Obtener todos los usuarios")
    @GetMapping
    public List<UsuarioResponse> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    @Operation(summary = "Obtener usuario por ID")
    @GetMapping("/{id}")
    public UsuarioResponse getUsuarioById(@PathVariable Long id) {
        return usuarioService.getUsuarioById(id);
    }

    @Operation(summary = "Crear un usuario")
    @PostMapping("/add")
    public ResponseEntity<String> addUsuario(@RequestBody UsuarioRequest usuario) {
        return usuarioService.addUsuario(usuario);
    }
    
    @Operation(summary = "Actualizar un usuario")
    @PutMapping("/put/{id}")
    public ResponseEntity<String> putUsuario(@PathVariable Long id, @RequestBody UsuarioUpdateRequest usuario) {
        return usuarioService.putUsuario(id, usuario);
    }

    @Operation(summary = "Eliminar un usuario")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUsuario(@PathVariable Long id) {
        return usuarioService.deleteUsuario(id);
    }
    
    //REQUERIMIENTOS DEL ENUNCIADO

    // Fue una prueba para entender la comunicacion entre 2 microservicios utilizando client OpenFeign
    @Operation(summary = "Obtener monopatines cercanos")
    @GetMapping("/monopatines/cercanos/{latitud}/{longitud}/{radio}")
    public List<MonopatinesCercanosDTO> obtenerMonopatinesCercanos(@PathVariable Double latitud, @PathVariable double longitud, @PathVariable double radio) {
        // Llamar al microservicio de Monopatines para obtener los cercanos
        return monopatinClient.obtenerMonopatinesCercanos(latitud, longitud, radio);
    }

    @Operation(summary = "Login de usuario")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        return usuarioService.login(request.getEmail(), request.getPassword())
                .map(usuario -> {
                    // Si el login es válido, generar el token JWT
                    String token = JwtUtil.generateToken(usuario.getEmail(), usuario.getRol());
                    return ResponseEntity.ok(token);
                })
                .orElse(ResponseEntity.status(401).body("Credenciales inválidas"));
    }
}
