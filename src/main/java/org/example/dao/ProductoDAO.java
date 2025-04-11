package org.example.dao;

public class ProductoDAO {
    private Connection connection;

    public PersonaDao(Connection conn) {
        this.conn = connection;
    }

    public void addProducto(Producto producto) {
        String query = "INSERT INTO Prodcuto (idProducto, nombre, valor) VALUES (?, ?, ?)";
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, producto.getIdPersona());
            ps.setString(2, producto.getNombre());
            ps.setFloat(3, producto.getValor());
            ps.executeUpdate();
            System.out.println("Producto insertado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
