package modelo;

import java.time.LocalDate;

public class Inventario {

    int id;
    String nombreRepuesto;
    int cantidad;
    LocalDate ultimaActualizacion;

    public Inventario() {
    }
    
    public Inventario(String nombreRepuesto, int cantidad, LocalDate ultimaActualizacion) {
        this.nombreRepuesto = nombreRepuesto;
        this.cantidad = cantidad;
        this.ultimaActualizacion = ultimaActualizacion;
    }

    public Inventario(int id, String nombreRepuesto, int cantidad, LocalDate ultimaActualizacion) {
        this.id = id;
        this.nombreRepuesto = nombreRepuesto;
        this.cantidad = cantidad;
        this.ultimaActualizacion = ultimaActualizacion;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreRepuesto() {
        return nombreRepuesto;
    }

    public void setNombreRepuesto(String nombreRepuesto) {
        this.nombreRepuesto = nombreRepuesto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDate getUltimaActualizacion() {
        return ultimaActualizacion;
    }

    public void setUltimaActualizacion(LocalDate ultimaActualizacion) {
        this.ultimaActualizacion = ultimaActualizacion;
    }
    
    
    
}
