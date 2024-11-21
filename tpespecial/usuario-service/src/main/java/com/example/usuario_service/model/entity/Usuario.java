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
    private String password;

    @Column(nullable = false)
    private String telefono;

    @Column(nullable = false)
    private Rol rol;

    public Usuario(String nombre, String apellido, String telefono, String email, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
        this.rol = Rol.USUARIO;
    }

    public void asociarCuenta(Cuenta cuenta) {
        if (!this.cuentas.contains(cuenta))
            this.cuentas.add(cuenta);
    }

    public enum Rol {
        USUARIO,
        MANTENIMIENTO,
        ADMIN
    }
}
