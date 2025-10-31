package edu.co.gym.model;
//ji
import java.util.Date;

public class Pago {
    private String id;
    private String detallesPago;
    private double valor;
    private Date  fechaPago;

    public Pago(String id, String detallesPago, double valor, Date fechaPago) {
        this.id = id;
        this.detallesPago = detallesPago;
        this.valor = valor;
        this.fechaPago = fechaPago;
    }
}
