package org.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.dto.ClienteDTO;
import org.example.entities.Cliente;

/**
 * Clase DAO para la entidad Cliente.
 * Gestiona las consultas sql a la base de datos
 * @author Piliavsky Pablo
 */
public class ClienteDAO {
    private final Connection connection;

    public ClienteDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Agrega una cliente a la base de datos
     * @param cliente cliente a agregar
     * @throws SQLException
     */
    public void addCliente(Cliente cliente) throws SQLException {
        String query = "INSERT INTO cliente (idCliente, nombre, email) VALUES (?, ?, ?)";

        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, cliente.getIdCliente());
        ps.setString(2, cliente.getNombre());
        ps.setString(3, cliente.getEmail());
        ps.executeUpdate();

        System.out.println("Cliente insertado exitosamente.");

        ps.close();
        connection.commit();
    }

    /**
     * Retorna una lista de clientes ordenada descendentemente por total de facturación
     * @return lista de clientes
     * @throws SQLException
     */
    public List<ClienteDTO> getClientesMayorFacturacion() throws SQLException {
        String query = "SELECT DISTINCT c.*, SUM(fp.cantidad * p.valor) as total_facturado " +
                        "FROM cliente c, factura f, facturaProducto fp, producto p " +
                        "WHERE c.idCliente = f.idCliente AND " +
                            "f.idFactura = fp.idFactura AND " +
                            "fp.idProducto = p.idProducto " +
                        "GROUP BY c.idCliente, c.nombre " +
                        "ORDER BY total_facturado DESC";

        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery(query);
        List<ClienteDTO> clientes = new ArrayList<>();

        while(rs.next()){
            String nombre = rs.getString("nombre");
            String email = rs.getString("email");
            int total_facturado = rs.getInt("total_facturado");

            ClienteDTO clienteDTO = new ClienteDTO(nombre, email, total_facturado);

            clientes.add(clienteDTO);
        }

        return clientes;
    }
}
