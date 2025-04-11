package org.example.factory;

import org.example.dao.ClienteDAO;
import org.example.dao.DAO_Persona;
import org.example.dao.FacturaDAO;
import org.example.dao.ProductoDAO;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.lang.Class.forName;

public class MysqlDAOFactory extends AbstractFactory{
    private static MysqlDAOFactory instance = null;

    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String uri = "jdbc:mysql://localhost:3306/db_example";
    public static Connection connection;

    //Constructor privado
    private MysqlDAOFactory(){}

    /**
     * Retorna la instancia mysql
     * @return instance
     */
    public static synchronized MysqlDAOFactory getInstance(){
        if (instance == null)
            instance = new MysqlDAOFactory();
        return instance;
    }

    /**
     * Establace una conexión mysql
     * @return connection, conexión mysql
     * @throws SQLException
     */
    public static Connection createConnection() throws SQLException {
        if(connection != null)
            return connection;

        try{
            Class.forName(DRIVER).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                 | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        connection = DriverManager.getConnection(uri, "root", "");
        connection.setAutoCommit(false);

        return connection;
    }

    /**
     * Cierra la conexión mysql
     * @throws SQLException
     */
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    @Override
    public FacturaDAO getFacturaDAO() throws SQLException {
        return new FacturaDAO(createConnection());
    }

    @Override
    public ClienteDAO getClienteDAO() throws SQLException {
        return new ClienteDAO(createConnection());
    }

    @Override
    public ProductoDAO getProductoDAO() throws SQLException {
        return new ProductoDAO(createConnection());
    }

    @Override
    public FacturaProductoDAO getFacturaProductoDAO() throws SQLException {
        return new FacturaProductoDAO(createConnection());
    }
}
