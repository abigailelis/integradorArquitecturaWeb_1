package org.example.factory;

import java.sql.SQLException;

import org.example.dao.ClienteDAO;
import org.example.dao.FacturaDAO;
import org.example.dao.FacturaProductoDAO;
import org.example.dao.ProductoDAO;

public abstract class AbstractFactory {
    public static final int MYSQL_JDBC = 1;
    public static final int DERBY_JDBC = 2;
    public abstract ProductoDAO getProductoDAO()throws SQLException;
    public abstract FacturaDAO getFacturaDAO() throws SQLException;
    public abstract ClienteDAO getClienteDAO() throws SQLException;
    public abstract FacturaProductoDAO getFacturaProductoDAO() throws SQLException;
    public static AbstractFactory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
            case MYSQL_JDBC : {
                return MysqlDAOFactory.getInstance();
            }
            case DERBY_JDBC: return null;
            default: return null;
        }
    }
}
