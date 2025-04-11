package org.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.example.entities.Producto;

public class ProductoDAO {
    private Connection connection;

    public ProductoDAO(Connection connection) {
        this.connection = connection;
    }

    public void addProducto(Producto producto) throws SQLException {
        String query = "INSERT INTO Prodcuto (idProducto, nombre, valor) VALUES (?, ?, ?)";
        PreparedStatement ps = null;

        ps = connection.prepareStatement(query);
        ps.setInt(1, producto.getIdProducto());
        ps.setString(2, producto.getNombre());
        ps.setFloat(3, producto.getValor());
        ps.executeUpdate();
        System.out.println("Producto insertado exitosamente.");

        if (ps != null) 
            ps.close();
        
        connection.commit();
    }

}
