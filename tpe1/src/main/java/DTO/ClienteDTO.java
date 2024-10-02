package DTO;

public class ClienteDTO {
    private int id;
    private String nombre;
    private float totalFacturado;

    public ClienteDTO(int id, String nombre, float totalFacturado) {
        this.id = id;
        this.nombre = nombre;
        this.totalFacturado = totalFacturado;
    }

    public int getIdCliente() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public float getTotalFacturado() {
        return totalFacturado;
    }

    @Override
    public String toString() {
        return "idCliente: " + id +
                ", nombre: '" + nombre + '\'' +
                ", facturado: " + totalFacturado +
                ".";
    }
}