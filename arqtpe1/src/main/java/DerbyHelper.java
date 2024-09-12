import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import Entidades.Cliente;
import Entidades.Factura;
import Entidades.Factura_Producto;
import Entidades.Producto;
import Factory.DerbyDAOFactory;

public class DerbyHelper {

    final String PATH = "arq-tpe1/arqtpe1/src/main/java/CSV/";
    DerbyDAOFactory fact = DerbyDAOFactory.getInstance();
    Connection conn = DerbyDAOFactory.createConnection();

    public void createTables() throws SQLException {
        System.out.println(this.conn);

        String tableCliente = "CREATE TABLE Cliente (" +
                "idCliente INT NOT NULL, " +
                "Nombre VARCHAR(500), " +
                "Email VARCHAR(150), " +
                "PRIMARY KEY (idCliente))";
        this.conn.prepareStatement(tableCliente).execute();
        this.conn.commit();

        String tableFactura = "CREATE TABLE Factura (" +
                "idFactura INT NOT NULL, " +
                "idCliente INT NOT NULL, " +
                "PRIMARY KEY (idFactura), " +
                "FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente))";
        this.conn.prepareStatement(tableFactura).execute();
        this.conn.commit();

        String tableProducto = "CREATE TABLE Producto (" +
                "idProducto INT NOT NULL, " +
                "nombre VARCHAR(45), " +
                "valor FLOAT NOT NULL, " +
                "PRIMARY KEY (idProducto))";
        this.conn.prepareStatement(tableProducto).execute();
        this.conn.commit();

        String tableFactura_Producto = "CREATE TABLE Factura_Producto (" +
                "idFactura INT NOT NULL, " +
                "idProducto INT NOT NULL, " +
                "cantidad INT, " +
                "PRIMARY KEY (idFactura, idProducto)) ";
        // "FOREIGN KEY (idFactura) REFERENCES Factura(idFactura), " +
        // "FOREIGN KEY (idProducto) REFERENCES Producto(idProducto))";
        this.conn.prepareStatement(tableFactura_Producto).execute();
        this.conn.commit();
    }

    private Iterable<CSVRecord> getData(String fileName) throws IOException {
        String path = PATH + fileName;
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path));

        Iterable<CSVRecord> data = parser.getRecords();

        return data;
    }

    public void insertData() throws Exception {
        this.InsertClientData(this.getData("clientes.csv"));
        this.InsertProductData(this.getData("productos.csv"));
        this.InsertProduct_TicketData(this.getData("facturas-productos.csv"));
        this.InsertTicketData(this.getData("facturas.csv"));
    }

    public void InsertClientData(Iterable<CSVRecord> data) throws Exception {
        for (CSVRecord row : data) {
            int id = Integer.parseInt(row.get(0));
            String nombre = row.get(1);
            String email = row.get(2);

            Cliente cliente = new Cliente(id, nombre, email);
            fact.getClienteDAO().insertCliente(cliente);
        }
    }

    public void InsertProductData(Iterable<CSVRecord> data) throws Exception {
        for (CSVRecord row : data) {
            int id = Integer.parseInt(row.get(0));
            String nombre = row.get(1);
            float valor = Float.parseFloat(row.get(2));

            Producto producto = new Producto(id, nombre, valor);
            fact.getProductoDAO().insertProducto(producto);
        }
    }

    public void InsertTicketData(Iterable<CSVRecord> data) throws Exception {
        for (CSVRecord row : data) {
            int idFactura = Integer.parseInt(row.get(0));
            int idCliente = Integer.parseInt(row.get(1));

            Factura factura = new Factura(idFactura, idCliente);
            fact.getFacturaDAO().insertFactura(factura);
        }
    }

    public void InsertProduct_TicketData(Iterable<CSVRecord> data) throws Exception {
        for (CSVRecord row : data) {
            int idFactura = Integer.parseInt(row.get(0));
            int idProducto = Integer.parseInt(row.get(1));
            int cantidad = Integer.parseInt(row.get(2));

            Factura_Producto facturaProducto = new Factura_Producto(idFactura, idProducto, cantidad);
            fact.getFactura_ProductoDAO().insertFactura_Producto(facturaProducto);
        }
    }

    public void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}