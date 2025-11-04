package uniquindio.edu.co.gym.model;

import java.time.LocalDate;

public class Estudiante extends Usuario {
    private String programa;
    private String semestre;
    private String facultad;

    public Estudiante(String nombre, String ID, String telefono, String direccion, String fechaNacimiento, String contrasena, LocalDate fechaCreacion, String programa, String semestre, String facultad) {
        super(nombre,ID,telefono,direccion,fechaNacimiento, contrasena, fechaCreacion);
        this.programa = programa;
        this.semestre = semestre;
        this.facultad = facultad;
    }
    @Override
    public double calcularDescuento(Membresia membresia) {
        Membresia membre = (membresia != null) ? membresia : this.getMembresia();
        if (membre == null) {
            return 0;
        }
        double valorConDescuento = membre.getCosto() - (membre.getCosto() * 0.30);
        return valorConDescuento;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }
}
