package org.example;

import org.example.dao.ClienteDAO;
import org.example.dao.FacturaDAO;
import org.example.dao.FacturaProductoDAO;
import org.example.dao.ProductoDAO;
import org.example.dto.ClienteDTO;
import org.example.factory.AbstractFactory;
import org.example.utils.HelperMysql;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        HelperMysql db_mysql = new HelperMysql();
        db_mysql.createTables();
        db_mysql.dropTables();
        db_mysql.createTables();
        db_mysql.populateDB();
        db_mysql.closeConnection();

        AbstractFactory chosenFactory = AbstractFactory.getDAOFactory(1);
        ProductoDAO producto = chosenFactory.getProductoDAO();
        ClienteDAO cliente = chosenFactory.getClienteDAO();
        FacturaDAO factura = chosenFactory.getFacturaDAO();
        FacturaProductoDAO facturaProducto = chosenFactory.getFacturaProductoDAO();

        //Obtener lista de clientes con mayor facturación
        List<ClienteDTO> clientesDTO = cliente.getClientesMayorFacturacion();

        for(ClienteDTO clienteDTO : clientesDTO){
            System.out.println(clienteDTO);
        }
    }
}