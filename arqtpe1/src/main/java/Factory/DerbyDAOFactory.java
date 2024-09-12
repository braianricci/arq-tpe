package Factory;

import DAO.ClienteDAO;
import DAO.FacturaDAO;
import DAO.Factura_ProductoDAO;
import DAO.ProductoDAO;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DerbyDAOFactory extends AbstractFactory {

    private static DerbyDAOFactory instance = null;

    public static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    public static final String uri = "jdbc:derby:MyDerbyDb;create=true";
    public static Connection conn;

    private DerbyDAOFactory() {
    }

    public static synchronized DerbyDAOFactory getInstance() {
        if (instance == null) {
            instance = new DerbyDAOFactory();
        }

        return instance;
    }

    public static Connection createConnection() {

        if (conn != null) {
            return conn;
        }

        String driver = DRIVER;
        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            conn = DriverManager.getConnection(uri);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ClienteDAO getClienteDAO() {
        return ClienteDAO.getInstance(createConnection());
    }

    @Override
    public FacturaDAO getFacturaDAO() {
        return FacturaDAO.getInstance(createConnection());
    }

    @Override
    public ProductoDAO getProductoDAO() {
        return ProductoDAO.getInstance(createConnection());
    }

    @Override
    public Factura_ProductoDAO getFactura_ProductoDAO() {
        return Factura_ProductoDAO.getInstance(createConnection());
    }
}