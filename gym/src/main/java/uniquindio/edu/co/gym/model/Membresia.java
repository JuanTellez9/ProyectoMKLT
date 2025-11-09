package uniquindio.edu.co.gym.model;
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

    /**
     * Constructor de la clase Membresia.
     * Inicializa todos los atributos con los valores proporcionados.
     *
     * @param id identificador unico de la membresia
     * @param costo costo de la membresia
     * @param fechaInicio fecha de inicio de la membresia
     * @param fechaVencimiento fecha en que vence la membresia
     * @param estado indica si la membresia esta activa o no
     * @param beneficio descripcion del beneficio asociado a la membresia
     * @param tipo tipo de membresia (por ejemplo, mensual, anual)
     * @param nivel nivel de la membresia (por ejemplo, basico, premium)
     */
    public Membresia(int id,double costo,Date fechaInicio,Date fechaVencimiento,boolean estado, String beneficio, Tipo tipo, Nivel nivel) {
        this.id = id;
        this.costo = costo;
        this.fechaInicio = fechaInicio;
        this.fechaVencimiento = fechaVencimiento;
        this.estado = estado;
        this.beneficio = beneficio;
        this.tipo = tipo;
        this.nivel = nivel;
    }

    /**
     * Verifica si la membresia esta activa en la fecha actual.
     *
     * @return true si la membresia esta activa, false en caso contrario
     */
    public boolean isActiva() {
        Date hoy = new Date();

        if (estado && fechaVencimiento != null && fechaVencimiento.after(hoy)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Retorna el identificador de la membresia.
     *
     * @return id de la membresia
     */
    public int getId() {
        return id;
    }

    /**
     * Asigna un nuevo identificador a la membresia.
     *
     * @param id nuevo identificador
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Retorna el costo de la membresia.
     *
     * @return costo de la membresia
     */
    public double getCosto() {
        return costo;
    }

    /**
     * Asigna un nuevo costo a la membresia.
     *
     * @param costo nuevo valor del costo
     */
    public void setCosto(double costo) {
        this.costo = costo;
    }

    /**
     * Retorna la fecha de inicio de la membresia.
     *
     * @return fecha de inicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Asigna una nueva fecha de inicio a la membresia.
     *
     * @param fechaInicio nueva fecha de inicio
     */
    public void setFechaInicio(Date fechaInicio) {this.fechaInicio = fechaInicio; }

    /**
     * Retorna la fecha de vencimiento de la membresia.
     *
     * @return fecha de vencimiento
     */
    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * Asigna una nueva fecha de vencimiento a la membresia.
     *
     * @param fechaVencimiento nueva fecha de vencimiento
     */
    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    /**
     * Retorna el estado de la membresia.
     *
     * @return true si esta activa, false si no lo esta
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * Asigna un nuevo estado a la membresia.
     *
     * @param estado nuevo estado de la membresia
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    /**
     * Retorna el beneficio asociado a la membresia.
     *
     * @return beneficio de la membresia
     */
    public String getBeneficio() {
        return beneficio;
    }

    /**
     * Asigna un nuevo beneficio a la membresia.
     *
     * @param beneficio nuevo beneficio
     */
    public void setBeneficio(String beneficio) {
        this.beneficio = beneficio;
    }

    /**
     * Retorna el tipo de la membresia.
     *
     * @return tipo de membresia
     */
    public Tipo getTipo() {
        return tipo;
    }

    /**
     * Asigna un nuevo tipo a la membresia.
     *
     * @param tipo nuevo tipo de membresia
     */
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    /**
     * Retorna el nivel de la membresia.
     *
     * @return nivel de la membresia
     */
    public Nivel getNivel() {
        return nivel;
    }

    /**
     * Asigna un nuevo nivel a la membresia.
     *
     * @param nivel nuevo nivel de la membresia
     */
    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

}
