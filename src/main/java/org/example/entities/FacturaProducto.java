package org.example.entities;

public class FacturaProducto {
    private int idFactura;
    private int idCliente;
    private int cantidad;

    public FacturaProducto(int idFactura, int idCliente, int cantidad) {
        this.idFactura = idFactura;
        this.idCliente = idCliente;
        this.cantidad= cantidad;
    }

    public int getIdProducto() {
        return idFactura;
    }

    public int getIdCliente() {
        return idCliente;
    }
    public int getCantidad(){
        return cantidad;
    }

}