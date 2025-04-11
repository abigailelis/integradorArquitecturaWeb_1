package org.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.example.entities.Factura;

public class FacturaDAO {
    private Connection connection;

    public FacturaDAO(Connection connection) {
        this.connection = connection;
    }

    public void addFactura (Factura factura) throws SQLException{
        String query = "INSERT INTO factura (id, clienteId) VALUES (?, ?)";
        PreparedStatement ps = null;

        ps = connection.prepareStatement(query);
        ps.setInt(1, factura.getId());
        ps.setInt(2, factura.getIdCliente());
        ps.executeUpdate();

        System.out.println("Factura insertada exitosamente.");

        if(ps!=null)
            ps.close();
        connection.commit();
    }

    

}
