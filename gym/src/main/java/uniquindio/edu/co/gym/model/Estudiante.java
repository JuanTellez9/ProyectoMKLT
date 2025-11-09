package uniquindio.edu.co.gym.model;

import java.time.LocalDate;

public class Estudiante extends Usuario {
    private String programa;
    private String semestre;
    private String facultad;

    /**
     * Constructor de la clase Estudiante.
     *
     * @param nombre nombre del estudiante
     * @param id identificacion del estudiante
     * @param telefono telefono del estudiante
     * @param direccion direccion del estudiante
     * @param fechaNacimiento fecha de nacimiento del estudiante
     * @param contrasena contrasena de acceso del estudiante
     * @param fechaCreacion fecha de creacion del registro
     * @param programa programa academico del estudiante
     * @param semestre semestre actual del estudiante
     * @param facultad facultad a la que pertenece el estudiante
     */
    public Estudiante(String nombre, String id, String telefono, String direccion, String fechaNacimiento, String contrasena, LocalDate fechaCreacion, String programa, String semestre, String facultad) {
        super(nombre,id,telefono,direccion,fechaNacimiento, contrasena, fechaCreacion);
        this.programa = programa;
        this.semestre = semestre;
        this.facultad = facultad;
    }
    /**
     * Metodo que calcula el valor de una membresia aplicando un descuento del 30%.
     * Si la membresia proporcionada es nula, se utiliza la membresia del objeto actual.
     * Si no existe ninguna membresia, retorna 0.
     *
     * @param membresia objeto de tipo Membresia al que se le aplicara el descuento
     * @return valor de la membresia con el descuento aplicado o 0 si no hay membresia
     */
    @Override
    public double calcularDescuento(Membresia membresia) {
        Membresia membre = (membresia != null) ? membresia : this.getMembresia();
        if (membre == null) {
            return 0;
        }
        double valorConDescuento = membre.getCosto() - (membre.getCosto() * 0.30);
        return valorConDescuento;
    }


    /**
     * Metodo que obtiene el programa academico del estudiante.
     *
     * @return programa academico del estudiante
     */
    public String getPrograma() {
        return programa;
    }

    /**
     * Metodo que establece el programa academico del estudiante.
     *
     * @param programa programa academico a asignar
     */
    public void setPrograma(String programa) {
        this.programa = programa;
    }

    /**
     * Metodo que obtiene el semestre actual del estudiante.
     *
     * @return semestre del estudiante
     */
    public String getSemestre() {
        return semestre;
    }


    /**
     * Metodo que establece el semestre actual del estudiante.
     *
     * @param semestre semestre a asignar
     */
    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }


    /**
     * Metodo que obtiene la facultad a la que pertenece el estudiante.
     *
     * @return facultad del estudiante
     */
    public String getFacultad() {
        return facultad;
    }


    /**
     * Metodo que establece la facultad a la que pertenece el estudiante.
     *
     * @param facultad facultad a asignar al estudiante
     */
    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }
}
