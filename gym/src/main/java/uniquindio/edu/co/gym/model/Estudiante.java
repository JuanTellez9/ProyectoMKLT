package uniquindio.edu.co.gym.model;

import java.time.LocalDate;

public class Estudiante extends Persona {
    private String programa;
    private String semestre;
    private String facultad;

    public Estudiante(String nombre, String ID, String telefono, String direccion, String fechaNacimiento, String contraseña, LocalDate fechaCreacion,String programa, String semestre, String facultad) {
        super(nombre,ID,telefono,direccion,fechaNacimiento);
        this.programa = programa;
        this.semestre = semestre;
        this.facultad = facultad;
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
