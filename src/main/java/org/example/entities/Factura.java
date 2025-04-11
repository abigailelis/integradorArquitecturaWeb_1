package org.example.entities;

public class Factura {
    private int idFactura;
    private int idCliente;

    public Factura() {
    }

    public Factura(int id, int idCliente) {
        this.idFactura = id;
        this.idCliente = idCliente;
    }

    public int getId() {
        return idFactura;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "id=" + idFactura +
                ", idCliente=" + idCliente +
                '}';
    }
}
