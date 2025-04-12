package org.example.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class HelperMysql {
    private Connection connection = null;

    public HelperMysql() throws SQLException {
        String driver = "com.mysql.cj.jdbc.Driver";
        String uri = "jdbc:mysql://localhost:3306/integrador1";

        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException |
                 NoSuchMethodException | SecurityException | ClassNotFoundException |
                 InvocationTargetException e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            connection = DriverManager.getConnection(uri, "root", "");
            connection.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        if (connection != null){
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void dropTables() throws SQLException {
        String[] tablas = {"FacturaProducto", "Factura", "Cliente", "Producto"};
        for (String tabla : tablas) {
            String drop = "DROP TABLE IF EXISTS " + tabla;
            connection.prepareStatement(drop).execute();
            connection.commit();
        }
    }

    public void createTables() throws SQLException {
        String tableProducto = "CREATE TABLE IF NOT EXISTS Producto (" +
                "idProducto INT PRIMARY KEY, " +
                "nombre VARCHAR(100), " +
                "valor FLOAT)";

        String tableCliente = "CREATE TABLE IF NOT EXISTS Cliente (" +
                "idCliente INT PRIMARY KEY, " +
                "nombre VARCHAR(100), " +
                "email VARCHAR(100))";

        String tableFactura = "CREATE TABLE IF NOT EXISTS Factura (" +
                "idFactura INT PRIMARY KEY, " +
                "idCliente INT, " +
                "FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente))";

        String tableFacturaProducto = "CREATE TABLE IF NOT EXISTS FacturaProducto (" +
                "idFactura INT, " +
                "idProducto INT, " +
                "cantidad INT, " +
                "FOREIGN KEY (idFactura) REFERENCES Factura(idFactura), " +
                "FOREIGN KEY (idProducto) REFERENCES Producto(idProducto))";

        connection.prepareStatement(tableProducto).execute();
        connection.prepareStatement(tableCliente).execute();
        connection.prepareStatement(tableFactura).execute();
        connection.prepareStatement(tableFacturaProducto).execute();
        connection.commit();
    }

    public void populateDB() throws Exception {
        insertProductos();
        insertClientes();
        insertFacturas();
        insertFacturaProducto();
    }

    private void insertProductos() throws Exception {
        for (CSVRecord row : getData("productos.csv")) {
            if (row.size() >= 3) {
                int id = Integer.parseInt(row.get(0));
                String nombre = row.get(1);
                float valor = Float.parseFloat(row.get(2));


                String insert = "INSERT INTO Producto (idProducto, nombre, valor) VALUES (?, ?, ?)";
                try (PreparedStatement ps = connection.prepareStatement(insert)) {
                    ps.setInt(1, id);
                    ps.setString(2, nombre);
                    ps.setFloat(3, valor);
                    ps.executeUpdate();
                }
            }
        }
        connection.commit();
        System.out.println("Productos insertados.");
    }

    private void insertClientes() throws Exception {
        for (CSVRecord row : getData("clientes.csv")) {
            if (row.size() >= 3) {
                int id = Integer.parseInt(row.get(0));
                String nombre = row.get(1);
                String email = row.get(2);

                String insert = "INSERT INTO Cliente (idCliente, nombre, email) VALUES (?, ?, ?)";
                try (PreparedStatement ps = connection.prepareStatement(insert)) {
                    ps.setInt(1, id);
                    ps.setString(2, nombre);
                    ps.setString(3, email);
                    ps.executeUpdate();
                }
            }
        }
        connection.commit();
        System.out.println("Clientes insertados.");
    }

    private void insertFacturas() throws Exception {
        for (CSVRecord row : getData("facturas.csv")) {
            if (row.size() >= 2) {
                int idFactura = Integer.parseInt(row.get(0));
                int idCliente = Integer.parseInt(row.get(1));

                String insert = "INSERT INTO Factura (idFactura, idCliente) VALUES (?, ?)";
                try (PreparedStatement ps = connection.prepareStatement(insert)) {
                    ps.setInt(1, idFactura);
                    ps.setInt(2, idCliente);
                    ps.executeUpdate();
                }
            }
        }
        connection.commit();
        System.out.println("Facturas insertadas.");
    }

    private void insertFacturaProducto() throws Exception {
        for (CSVRecord row : getData("facturas-productos.csv")) {
            if (row.size() >= 3) {
                int idFactura = Integer.parseInt(row.get(0));
                int idProducto = Integer.parseInt(row.get(1));
                int cantidad = Integer.parseInt(row.get(2));

                String insert = "INSERT INTO FacturaProducto (idFactura, idProducto, cantidad) VALUES (?, ?, ?)";
                try (PreparedStatement ps = connection.prepareStatement(insert)) {
                    ps.setInt(1, idFactura);
                    ps.setInt(2, idProducto);
                    ps.setInt(3, cantidad);
                    ps.executeUpdate();
                }
            }
        }
        connection.commit();
        System.out.println("Factura-Producto insertado.");
    }

    private Iterable<CSVRecord> getData(String archivo) throws IOException {
        String path = "src/main/resources/" + archivo;
        Reader in = new FileReader(path);
        CSVParser parser = CSVFormat.EXCEL.parse(in);
        return parser.getRecords();
    }
}