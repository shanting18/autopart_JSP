package modelo;

import java.time.LocalDate;

public class Vendedor {

    int id;
    String nombre_tienda;
    String descripcion;
    double reputacion;
    LocalDate fecha_registro;

    public Vendedor() {
    }

    public Vendedor(String nombre_tienda, String descripcion, LocalDate fecha_registro) {
        this.nombre_tienda = nombre_tienda;
        this.descripcion = descripcion;
        this.fecha_registro = fecha_registro;
    }

    public Vendedor(int id, String nombre_tienda, String descripcion, double reputacion, LocalDate fecha_registro) {
        this.id = id;
        this.nombre_tienda = nombre_tienda;
        this.descripcion = descripcion;
        this.reputacion = reputacion;
        this.fecha_registro = fecha_registro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_tienda() {
        return nombre_tienda;
    }

    public void setNombre_tienda(String nombre_tienda) {
        this.nombre_tienda = nombre_tienda;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getReputacion() {
        return reputacion;
    }

    public void setReputacion(double reputacion) {
        this.reputacion = reputacion;
    }

    public LocalDate getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(LocalDate fecha_registro) {
        this.fecha_registro = fecha_registro;
    }
}
