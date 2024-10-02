package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Entidades.Factura;

public class FacturaDAO {

    private static FacturaDAO instance = null;
    private static Connection conn;

    private FacturaDAO() {
    }

    public static synchronized FacturaDAO getInstance(Connection connection) {
        conn = connection;

        if (instance == null) {
            instance = new FacturaDAO();
        }

        return instance;
    }

    public void insertFactura(Factura factura) throws SQLException {
        String query = "INSERT INTO Factura (idFactura,idCliente) VALUES (?, ?)";
        PreparedStatement ps = null;

        ps = conn.prepareStatement(query);
        ps.setInt(1, factura.getIdFactura());
        ps.setInt(2, factura.getIdCliente());
        ps.executeUpdate();
        ps.close();
        conn.commit();
    }
}