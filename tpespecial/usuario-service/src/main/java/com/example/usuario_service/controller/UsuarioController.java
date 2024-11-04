package com.example.usuario_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.usuario_service.service.UsuarioService;
import com.example.usuario_service.model.dto.UsuarioRequest;
import com.example.usuario_service.model.dto.UsuarioResponse;




@RestController
@RequestMapping("/usuario")
public class UsuarioController{

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioResponse> getAllUsuarios(){
        return usuarioService.getAllUsuarios();
    }

    @GetMapping("/{id}")
    public UsuarioResponse getUsuarioById(@PathVariable Long id){
        return usuarioService.getUsuarioById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addUsuario(@RequestBody UsuarioRequest usuario){
         this.usuarioService.addUsuario(usuario);
    }

    @PutMapping("/put/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putUsuario(@PathVariable Long id, @RequestBody UsuarioRequest usuario){
        usuarioService.putUsuario(id,usuario);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUsuario(@PathVariable Long id){
        usuarioService.deleteUsuario(id);
    }
}
