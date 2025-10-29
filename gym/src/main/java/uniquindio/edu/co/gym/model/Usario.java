package uniquindio.edu.co.gym.model;

import java.time.LocalDate;

public abstract class Usario extends Persona {
    private String contraseña;
    private LocalDate fechaCreacion;

    public Usario(String nombre, String ID, String telefono, String direccion, String fechaNacimiento, String contraseña, LocalDate fechaCreacion) {
        super(nombre,ID,telefono,direccion,fechaNacimiento);
        this.contraseña=contraseña;
        this.fechaCreacion=fechaCreacion;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
