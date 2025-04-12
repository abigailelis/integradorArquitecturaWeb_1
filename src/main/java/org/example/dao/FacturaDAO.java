package org.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.example.entities.Factura;

/**
 * Clase DAO para la entidad Factura.
 * Gestiona las consultas sql a la base de datos
 * @author Elis Abigail
 */

public class FacturaDAO {
    private final Connection connection;

    public FacturaDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Agrega una factura a la base de datos
     * @param factura factura a agregar
     * @throws SQLException
     */
    public void addFactura (Factura factura) throws SQLException{
        String query = "INSERT INTO factura (id, clienteId) VALUES (?, ?)";

        PreparedStatement ps = connection.prepareStatement(query);

        ps.setInt(1, factura.getId());
        ps.setInt(2, factura.getIdCliente());
        ps.executeUpdate();

        System.out.println("Factura insertada exitosamente.");

        ps.close();
        connection.commit();
    }

    /**
     * Devuelve una lista de todas las facturas de la base de datos.
     */
    public List<Factura> getFacturas() throws SQLException {
        String query = "SELECT * FROM factura";
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery(query);
        List<Factura> facturas = new ArrayList<>();


        while(rs.next()){
            int idFactura = rs.getInt("idFactura");
            int idCliente = rs.getInt("idCliente");
            Factura factura = new Factura(idFactura, idCliente);
            facturas.add(factura);
        }

        ps.close();
        connection.commit();

        return facturas;
    }
}
