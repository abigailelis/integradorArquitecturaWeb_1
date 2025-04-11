package org.example.factory;

public abstract class AbstractFactory {
    public static final int MYSQL_JDBC = 1;
    public static final int DERBY_JDBC = 2;
    public abstract ProductoDao getProductoDAO();
    public abstract FacturaDao getFacturaDAO();
    public abstract ClienteDao getClienteDAO();
    public abstract FacturaProductoDao getFacturaProductoDAO();
    public static AbstractFactory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
            case MYSQL_JDBC : {
                return MySQLDAOFactory.getInstance();
            }
            case DERBY_JDBC: return null;
            default: return null;
        }
    }
}
