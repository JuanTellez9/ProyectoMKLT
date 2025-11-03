package uniquindio.edu.co.gym.model;

import java.time.LocalDate;
import java.util.Date;

public class Membresia {
    private int id;
    private double costo;
    private Date fechaInicio;
    private Date fechaVencimiento;
    private boolean estado;
    private String beneficio;
    private Tipo tipo;
    private Nivel nivel;

    public Membresia(int id,double costo,Date fechaInicio,Date fechaVencimiento,boolean estado, String beneficio) {
        this.id = id;
        this.costo = costo;
        this.fechaInicio = fechaInicio;
        this.fechaVencimiento = fechaVencimiento;
        this.estado = estado;
        this.beneficio = beneficio;
        this.tipo = tipo;
        this.nivel = nivel;
    }

    public boolean isActiva() {
        Date hoy = new Date();

        if (estado && fechaVencimiento != null && fechaVencimiento.after(hoy)) {
            return true;
        } else {
            return false;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {this.fechaInicio = fechaInicio; }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        fechaVencimiento = fechaVencimiento;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getBeneficio() {
        return beneficio;
    }

    public void setBeneficio(String beneficio) {
        this.beneficio = beneficio;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    @Override
    public String toString() {
        return "Membresia{" +
                "id=" + id +
                ", costo=" + costo +
                ", fechaInicio=" + fechaInicio +
                ", fechaVencimiento=" + fechaVencimiento +
                ", estado=" + estado +
                ", beneficio='" + beneficio + '\'' +
                ", tipo=" + tipo +
                ", nivel=" + nivel +
                '}';
    }
}
