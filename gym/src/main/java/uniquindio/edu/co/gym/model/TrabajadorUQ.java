package uniquindio.edu.co.gym.model;

import java.time.LocalDate;

public class TrabajadorUQ extends Usuario {

    private String cargo;
    private String area;
    private Beneficio beneficio;

    /**
     * Constructor de la clase TrabajadorUQ.
     * @param nombre nombre del trabajador
     * @param id identificacion del trabajador
     * @param telefono numero de contacto del trabajador
     * @param direccion direccion del trabajador
     * @param fechaNacimiento fecha de nacimiento del trabajador
     * @param contrasena contrasena de acceso
     * @param fechaCreacion fecha en que se creo el registro
     * @param cargo cargo que ocupa el trabajador
     * @param area area o dependencia donde trabaja
     */
    public TrabajadorUQ(String nombre, String id, String telefono, String direccion, String fechaNacimiento, String contrasena, LocalDate fechaCreacion, String cargo, String area) {
        super(nombre,id,telefono,direccion,fechaNacimiento,contrasena,fechaCreacion);
        this.cargo = cargo;
    }

    /**
     * Calcula el valor de la membresia aplicando un descuento del 15%.
     * @param membresia objeto membresia al cual se aplica el descuento
     * @return valor total con el descuento aplicado o 0 si no existe membresia
     */
    @Override
    public double calcularDescuento(Membresia membresia) {
        Membresia membre = (membresia != null) ? membresia : this.getMembresia();
        if (membre == null) {
            return 0;
        }
        double valorConDescuento = membre.getCosto() - (membre.getCosto() * 0.15);
        return valorConDescuento;
    }

    /**
     * Obtiene el beneficio asignado al trabajador.
     * @return objeto Beneficio del trabajador
     */
    public Beneficio getBeneficio() {
        return beneficio;
    }

    /**
     * Asigna un beneficio al trabajador.
     * @param beneficio objeto Beneficio a asignar
     */
    public void setBeneficio(Beneficio beneficio) {
        this.beneficio = beneficio;
    }

    /**
     * Obtiene el cargo del trabajador.
     * @return texto con el cargo
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * Asigna el cargo al trabajador.
     * @param cargo texto con el nuevo cargo
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    /**
     * Obtiene el area del trabajador.
     * @return texto con el area
     */
    public String getArea() {
        return area;
    }

    /**
     * Asigna el area del trabajador.
     * @param area texto con el area
     */
    public void setArea(String area) {
        this.area = area;
    }
}
