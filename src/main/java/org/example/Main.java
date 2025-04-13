package org.example;

import org.example.dao.ClienteDAO;
import org.example.dao.FacturaDAO;
import org.example.dao.FacturaProductoDAO;
import org.example.dao.ProductoDAO;
import org.example.dto.ClienteDTO;
import org.example.dto.ProductoDTO;
import org.example.factory.AbstractFactory;
import org.example.utils.HelperMysql;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        HelperMysql db_mysql = new HelperMysql();
        db_mysql.dropTables();
        db_mysql.createTables();
        db_mysql.populateDB();
        db_mysql.closeConnection();

        AbstractFactory chosenFactory = AbstractFactory.getDAOFactory(1);
        ProductoDAO producto = chosenFactory.getProductoDAO();
        ClienteDAO cliente = chosenFactory.getClienteDAO();


        /**
         * Obtiene una lista ordenada de clientes bajo el criterio de mayor a menor facturación
         */
        List<ClienteDTO> clientesDTO = cliente.getClientesMayorFacturacion();

        System.out.println("\n\nLista de clientes ordenados por mayor facturacion: ");

        for(ClienteDTO clienteDTO : clientesDTO){
            System.out.println(clienteDTO);
        }

        System.out.println("\nCantidad de clientes encontrados: " + clientesDTO.size());

        /**
         * Obtiene el producto que más recaudó, calculando la cantidad * el valor del mismo
         * en la totalidad de las facturas en las que se encuentre
         */
        ProductoDTO productoDTO = producto.getProductoMayorRecaudacion();

        if (productoDTO == null) {
            System.out.println("\n\n¡No se encontró el producto!");
        }else {
            System.out.println("\n\nEl producto que mas recaudó es: " + productoDTO);
        }

    }
}