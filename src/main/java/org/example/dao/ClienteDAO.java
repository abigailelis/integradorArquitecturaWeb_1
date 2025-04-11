package org.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ClienteDAO {

    private Connection connection;

    public ClienteDAO(Connection connection) {
        this.connection = connection;
    }

    public void addCliente(Cliente cliente) throws SQLException {
        String query = "INSERT INTO cliente (idCliente, nombre, email) VALUES (?, ?, ?)";
        PreparedStatement ps = null;

        ps = connection.prepareStatment(query);
        ps.setInt(1, cliente.getIdCliente());
        ps.setString(2, cliente.getNombre());
        ps.setString(3, cliente.getEmail());
        ps.executeUpdate();

        System.out.println("Cliente insertado exitosamente.");

        if (ps != null) {
            ps.close();
        }
        connection.commit();

    }

}
