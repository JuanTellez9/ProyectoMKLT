package uniquindio.edu.co.gym.model;


import java.util.Date;

public class Pago {
    private String id;
    private String detallesPago;
    private double valor;
    private Date fechaPago;
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Pago(String id, String detallesPago, double valor, Date fechaPago, Usuario usuario) {
        this.id = id;
        this.detallesPago = detallesPago;
        this.valor = valor;
        this.fechaPago = fechaPago;
        this.usuario=usuario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDetallesPago() {
        return detallesPago;
    }

    public void setDetallesPago(String detallesPago) {
        this.detallesPago = detallesPago;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }
}
