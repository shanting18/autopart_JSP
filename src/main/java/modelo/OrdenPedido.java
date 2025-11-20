package modelo;
import java.time.LocalDate;

public class OrdenPedido {

    int id;
    LocalDate fecha;
    String estado;
    Double total;

    public OrdenPedido() {
    }
    
    public OrdenPedido(LocalDate fecha, String estado, Double total) {
        this.fecha = fecha;
        this.estado = estado;
        this.total = total;
    }

    public OrdenPedido(int id, LocalDate fecha, String estado, Double total) {
        this.id = id;
        this.fecha = fecha;
        this.estado = estado;
        this.total = total;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
