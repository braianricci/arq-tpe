package com.example.usuario_service.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.Setter;
import java.util.List;

import java.util.ArrayList;

@Entity
@Data
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToMany(mappedBy = "usuarios", cascade = CascadeType.ALL)
    private List<Cuenta> cuentas = new ArrayList<>();

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String telefono;

    public Usuario(String nombre, String apellido, String email, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
    }

    public void asociarCuenta(Cuenta cuenta) {
        if (!this.cuentas.contains(cuenta))
            this.cuentas.add(cuenta);
    }

}
