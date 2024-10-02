package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.ClienteDTO;
import Entidades.Cliente;

public class ClienteDAO {

    private static ClienteDAO instance = null;

    private static Connection conn;

    private ClienteDAO() {
    }

    public static synchronized ClienteDAO getInstance(Connection connection) {
        conn = connection;

        if (instance == null) {
            instance = new ClienteDAO();
        }

        return instance;
    }

    public void insertCliente(Cliente cliente) throws SQLException {
        String query = "INSERT INTO Cliente (idCliente, nombre, email) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(query);

        ps = conn.prepareStatement(query);
        ps.setInt(1, cliente.getId());
        ps.setString(2, cliente.getNombre());
        ps.setString(3, cliente.getEmail());
        ps.executeUpdate();
        ps.close();
        conn.commit();
    }

    public List<ClienteDTO> getClientesPorFacturacion() throws SQLException {
        List<ClienteDTO> clientes = new ArrayList<>();

        String query = "SELECT c.idCliente, c.nombre, SUM(p.valor * fp.cantidad) " +
                "AS total_facturado FROM Cliente c " +
                "JOIN Factura f ON c.idCliente = f.idCliente " +
                "JOIN Factura_Producto fp ON f.idFactura = fp.idFactura " +
                "JOIN Producto p ON fp.idProducto = p.idProducto " +
                "GROUP BY c.idCliente, c.nombre ORDER BY total_facturado DESC";

        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("idCliente");
            String nombre = rs.getString("nombre");
            float totalFacturado = rs.getFloat("total_facturado");

            ClienteDTO clienteDTO = new ClienteDTO(id, nombre, totalFacturado);
            clientes.add(clienteDTO);
        }

        ps.close();
        conn.commit();
        return clientes;
    }
}