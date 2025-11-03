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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCostp() {
        return costp;
    }

    public void setCostp(int costp) {
        this.costp = costp;
    }

    public Date getFechaInicio() {
        return FechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        FechaInicio = fechaInicio;
    }

    public Date getFechaVencimiento() {
        return FechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        FechaVencimiento = fechaVencimiento;
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
}
