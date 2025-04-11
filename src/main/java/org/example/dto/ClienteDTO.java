package org.example.dto;

public class ClienteDTO {
    private String nombre;
    private String email;
    private int totalFacturado;

    public ClienteDTO() {}

    public ClienteDTO(String nombre, String email, int totalFacturado) {
        this.nombre = nombre;
        this.email = email;
        this.totalFacturado = totalFacturado;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail(){
        return email;
    }

    public int getTotalFacturado() {
        return totalFacturado;
    }

    @Override
    public String toString(){
        return "Nombre: " + this.nombre + ", email: " + this.email + ", total facturado: " + this.totalFacturado;
    }

}
