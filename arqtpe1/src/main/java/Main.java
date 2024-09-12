import java.sql.SQLException;
import java.util.List;

import DAO.ClienteDAO;
import DAO.ProductoDAO;
import DTO.ClienteDTO;
import DTO.ProductoDTO;
import Factory.AbstractFactory;

public class Main {
    public static void main(String[] args) throws SQLException {

        DerbyHelper helper = new DerbyHelper();

        try {
            helper.createTables();
            helper.insertData();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // consigna 3
        AbstractFactory fact = AbstractFactory.getDAOFactory(1);
        ProductoDAO p = fact.getProductoDAO();
        ProductoDTO prodMasRecaudo = p.getProdMasRecaudo();
        System.out.println(prodMasRecaudo.toString());

        // consigna 4
        ClienteDAO c = fact.getClienteDAO();
        List<ClienteDTO> clientes = c.getClientesPorFacturacion();
        for (ClienteDTO cliente : clientes) {
            System.out.println(cliente.toString());
        }

        helper.closeConnection();
    }
}