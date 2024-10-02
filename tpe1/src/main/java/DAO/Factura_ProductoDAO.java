package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Entidades.Factura_Producto;

public class Factura_ProductoDAO {

    private static Factura_ProductoDAO instance = null;
    private static Connection conn;

    private Factura_ProductoDAO() {
    }

    public static synchronized Factura_ProductoDAO getInstance(Connection connection) {
        conn = connection;

        if (instance == null) {
            instance = new Factura_ProductoDAO();
        }

        return instance;
    }

    public void insertFactura_Producto(Factura_Producto facturaProducto) throws SQLException {
        String query = "INSERT INTO Factura_Producto (idFactura, idProducto, cantidad) VALUES (?, ?, ?)";
        PreparedStatement ps = null;

        ps = conn.prepareStatement(query);
        ps.setInt(1, facturaProducto.getIdFactura());
        ps.setInt(2, facturaProducto.getIdProducto());
        ps.setInt(3, facturaProducto.getCantidad());
        ps.executeUpdate();
        ps.close();
        conn.commit();
    }
}