package uniquindio.edu.co.gym.model;

import java.time.LocalDate;

public class Externo extends Usuario {
    private String ocupacion;

    /**
     * Constructor de la clase Externo.
     *
     * @param nombre nombre del usuario externo
     * @param id identificacion del usuario
     * @param telefono telefono del usuario
     * @param direccion direccion del usuario
     * @param fechaNacimiento fecha de nacimiento del usuario
     * @param contrasena contrasena de acceso
     * @param fechaCreacion fecha en la que se crea el registro
     * @param ocupacion ocupacion o profesion del usuario externo
     */
    public Externo (String nombre, String id, String telefono, String direccion, String fechaNacimiento, String contrasena, LocalDate fechaCreacion, String ocupacion) {
        super(nombre,id,telefono,direccion,fechaNacimiento,contrasena,fechaCreacion);
        this.ocupacion = ocupacion;
    }
    /**
     * Metodo que calcula el valor de una membresia aplicando un descuento del 50%.
     * Si la membresia pasada como parametro es nula, se usa la membresia del objeto actual.
     * Si no existe ninguna membresia, retorna 0.
     *
     * @param membresia objeto de tipo Membresia al que se aplicara el descuento
     * @return valor de la membresia con el descuento aplicado o 0 si no hay membresia
     */
    @Override
    public double calcularDescuento(Membresia membresia) {
        Membresia membre = (membresia != null) ? membresia : this.getMembresia();
        if (membre == null) {
            return 0;
        }
        double valorConDescuento = membre.getCosto() - (membre.getCosto() * 0.5);
        return valorConDescuento;
    }

    /**
     * Metodo que obtiene la ocupacion del usuario externo.
     *
     * @return ocupacion del usuario
     */
    public String getOcupacion() {
        return ocupacion;
    }

    /**
     * Metodo que establece la ocupacion del usuario externo.
     *
     * @param ocupacion ocupacion o profesion a asignar
     */
    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }
}
