package Factory;

import DAO.ClienteDAO;
import DAO.FacturaDAO;
import DAO.Factura_ProductoDAO;
import DAO.ProductoDAO;

public abstract class AbstractFactory {
    public static final int DERBY_JDBC = 1;
    public static final int MYSQL_JDBC = 2;

    public abstract ClienteDAO getClienteDAO();

    public abstract FacturaDAO getFacturaDAO();

    public abstract Factura_ProductoDAO getFactura_ProductoDAO();

    public abstract ProductoDAO getProductoDAO();

    public static AbstractFactory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
            case DERBY_JDBC:
                return DerbyDAOFactory.getInstance();
            case MYSQL_JDBC:
                // return MySQLDAOFactory.getInstance();
                return null;
            default:
                return null;
        }
    }

}
