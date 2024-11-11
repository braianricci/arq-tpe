package com.example.usuario_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.usuario_service.client.MonopatinClient;
import com.example.usuario_service.model.dto.MonopatinesCercanosDTO;
import com.example.usuario_service.model.dto.UsuarioRequest;
import com.example.usuario_service.model.dto.UsuarioResponse;
import com.example.usuario_service.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private MonopatinClient monopatinClient;


    @GetMapping
    public List<UsuarioResponse> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    @GetMapping("/{id}")
    public UsuarioResponse getUsuarioById(@PathVariable Long id) {
        return usuarioService.getUsuarioById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addUsuario(@RequestBody UsuarioRequest usuario) {
        this.usuarioService.addUsuario(usuario);
    }

    @PutMapping("/put/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putUsuario(@PathVariable Long id, @RequestBody UsuarioRequest usuario) {
        usuarioService.putUsuario(id, usuario);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
    }

    //g.Fue prueba para la comunicacion entre 2 microservicios utilizando client OpenFeign
    @GetMapping("/monopatines/cercanos/{latitud}/{longitud}/{radio}")
    public List<MonopatinesCercanosDTO> obtenerMonopatinesCercanos(@PathVariable Double latitud, @PathVariable double longitud, @PathVariable double radio) {
        // Llamar al microservicio de Monopatines para obtener los cercanos
        return monopatinClient.obtenerMonopatinesCercanos(latitud, longitud, radio);
    }
}
