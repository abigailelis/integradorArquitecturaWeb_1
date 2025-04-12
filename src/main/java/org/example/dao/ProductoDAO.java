package org.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.example.entities.Producto;

/**
 * Clase DAO para la entidad Producto.
 * Gestiona las consultas sql a la base de datos
 * @author Cordeiro Federico
 */
public class ProductoDAO {
    private final Connection connection;

    public ProductoDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Agrega una producto a la base de datos
     * @param producto producto a agregar
     * @throws SQLException
     */
    public void addProducto(Producto producto) throws SQLException {
        String query = "INSERT INTO producto (idProducto, nombre, valor) VALUES (?, ?, ?)";

        PreparedStatement ps = connection.prepareStatement(query);

        ps.setInt(1, producto.getIdProducto());
        ps.setString(2, producto.getNombre());
        ps.setFloat(3, producto.getValor());
        ps.executeUpdate();
        System.out.println("Producto insertado exitosamente.");

        ps.close();
        connection.commit();
    }

}
