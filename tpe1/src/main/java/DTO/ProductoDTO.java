package DTO;

public class ProductoDTO {
    private int id;
    private String nombre;
    private float valor;

    public ProductoDTO(int id, String nombre, float valor) {
        this.id = id;
        this.nombre = nombre;
        this.valor = valor;
    }

    public int getIdCliente() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public float getTotalFacturado() {
        return valor;
    }

    @Override
    public String toString() {
        return "Producto que mas recaudo: idProducto: " + id +
                ", nombre: '" + nombre + '\'' +
                ", valor: " + valor +
                ".";
    }
}