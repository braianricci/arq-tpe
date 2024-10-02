package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DTO.ProductoDTO;
import Entidades.Producto;

public class ProductoDAO {

    private static ProductoDAO instance = null;
    private static Connection conn;

    private ProductoDAO() {
    }

    public static synchronized ProductoDAO getInstance(Connection connection) {
        conn = connection;

        if (instance == null) {
            instance = new ProductoDAO();
        }

        return instance;
    }

    public void insertProducto(Producto producto) throws SQLException {
        String query = "INSERT INTO Producto (idProducto, nombre, valor) VALUES (?, ?, ?)";
        PreparedStatement ps = null;

        ps = conn.prepareStatement(query);
        ps.setInt(1, producto.getId());
        ps.setString(2, producto.getNombre());
        ps.setFloat(3, producto.getValor());
        ps.executeUpdate();
        ps.close();
        conn.commit();
    }

    public ProductoDTO getProdMasRecaudo() throws SQLException {
        String query = "SELECT p.idProducto, p.nombre, SUM(fp.cantidad * p.valor) AS total_recaudado " +
                "FROM Producto p " +
                "JOIN Factura_Producto fp ON p.idProducto = fp.idProducto " +
                "GROUP BY p.idProducto, p.nombre " +
                "ORDER BY total_recaudado DESC " +
                "FETCH FIRST 1 ROW ONLY";

        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        ProductoDTO p = null;

        if (rs.next()) {
            int id = rs.getInt("idProducto");
            String nombre = rs.getString("nombre");
            float totalRecaudado = rs.getFloat("total_recaudado");
            p = new ProductoDTO(id, nombre, totalRecaudado);
        }

        ps.close();
        conn.commit();
        return p;

    }
}