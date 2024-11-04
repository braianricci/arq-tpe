package com.example.usuario_service.model.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Data
@NoArgsConstructor
public class Cuenta {
    

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false)
    private String mercadoPagoId;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Usuario> usuarios = new ArrayList<>();

    private int creditos;
    @Column(nullable = false)
    private LocalDate fechaDeCreacion;

    @Column(nullable = false)
    private boolean habilitada;


    public Cuenta(String mercadoPagoId) {
        this.mercadoPagoId = mercadoPagoId;
        this.fechaDeCreacion = LocalDate.now();
        this.creditos = 0;
        this.habilitada = true;
    }


    public void asociarUsuario(Usuario usuario){
        if(!this.usuarios.contains(usuario))
            this.usuarios.add(usuario);
    }

}
