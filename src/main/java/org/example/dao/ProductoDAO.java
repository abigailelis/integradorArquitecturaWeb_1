package org.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.example.dto.ProductoDTO;
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

    public ProductoDTO getProductoMayorRecaudacion() throws SQLException {
        String query = "SELECT p.*, fp.cantidad * p.valor as recaudado " +
                "FROM producto p INNER JOIN factura_producto fp" +
                "ON p.idProducto = fp.idProducto" +
                "ORDER BY recaudado DESC" +
                "LIMIT 1";

        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery(query);

        ProductoDTO productoDTO = null;

        String nombre = rs.getString("nombre");
        float recaudado = rs.getFloat("recaudado");

        productoDTO = new ProductoDTO(nombre, recaudado);

        return productoDTO;
    }




}
