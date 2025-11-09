package uniquindio.edu.co.gym.model;


import java.util.Date;

public class Pago {
    private String id;
    private String detallesPago;
    private double valor;
    private Date  fechaPago;
    private Administrador administrador;

    /**
     * Constructor de la clase Pago.
     * Inicializa los atributos con los valores proporcionados.
     *
     * @param id identificador unico del pago
     * @param detallesPago descripcion o detalles del pago
     * @param valor monto total del pago
     * @param fechaPago fecha en la que se realizo el pago
     * @param administrador objeto administrador responsable del registro del pago
     */
    public Pago(String id, String detallesPago, double valor, Date fechaPago, Administrador administrador) {
        this.id = id;
        this.detallesPago = detallesPago;
        this.valor = valor;
        this.fechaPago = fechaPago;
        this.administrador = administrador;
    }

    /**
     * Retorna el identificador del pago.
     *
     * @return id del pago
     */
    public String getId() {
        return id;
    }

    /**
     * Asigna un nuevo identificador al pago.
     *
     * @param id nuevo identificador del pago
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Retorna los detalles del pago.
     *
     * @return detalles del pago
     */
    public String getDetallesPago() {
        return detallesPago;
    }

    /**
     * Asigna nuevos detalles al pago.
     *
     * @param detallesPago descripcion o detalles del pago
     */
    public void setDetallesPago(String detallesPago) {
        this.detallesPago = detallesPago;
    }

    /**
     * Retorna el valor total del pago.
     *
     * @return valor del pago
     */
    public double getValor() {
        return valor;
    }

    /**
     * Asigna un nuevo valor al pago.
     *
     * @param valor monto del pago
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * Retorna la fecha en la que se realizo el pago.
     *
     * @return fecha del pago
     */
    public Date getFechaPago() {
        return fechaPago;
    }

    /**
     * Asigna una nueva fecha al pago.
     *
     * @param fechaPago nueva fecha del pago
     */

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    /**
     * Retorna el administrador que registro el pago.
     *
     * @return administrador responsable del pago
     */
    public Administrador getAdministrador() {
        return administrador;
    }

    /**
     * Asigna un nuevo administrador al pago.
     *
     * @param administrador nuevo administrador responsable del pago
     */
    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }
}
