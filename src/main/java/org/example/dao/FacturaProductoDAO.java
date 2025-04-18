package org.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.example.entities.FacturaProducto;

/**
 * Clase DAO para la entidad FacturaProducto.
 * Gestiona las consultas sql a la base de datos
 * @author Brost Simón
 */

 public class FacturaProductoDAO {
    
    private final Connection connection;

    public FacturaProductoDAO(Connection connection) {
        this.connection = connection;
    }
      
    
    /**
     * Agrega una facturaProducto a la base de datos
     * @param facturaProducto FacturaProducto a agregar
     * @throws SQLException
     */

     public void addFacturaProducto(FacturaProducto facturaProducto) throws SQLException {
        String query = "INSERT INTO facturaProducto (idProducto, idFactura, cantidad) VALUES (?, ?, ?)";

        PreparedStatement ps = connection.prepareStatement(query);

        ps.setInt(1, facturaProducto.getIdProducto());
        ps.setInt(2, facturaProducto.getIdCliente());
        ps.setFloat(3, facturaProducto.getCantidad());
        ps.executeUpdate();
        System.out.println("FacturaProducto insertado exitosamente.");

        ps.close();
        connection.commit();
    }
}
