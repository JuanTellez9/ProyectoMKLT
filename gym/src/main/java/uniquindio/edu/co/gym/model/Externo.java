package uniquindio.edu.co.gym.model;

import java.time.LocalDate;

public class Externo extends Usuario {
    private String ocupacion;

    public Externo (String nombre, String ID, String telefono, String direccion, String fechaNacimiento, String contraseña, LocalDate fechaCreacion, String ocupacion) {
        super(nombre,ID,telefono,direccion,fechaNacimiento,contraseña,fechaCreacion);
        this.ocupacion = ocupacion;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }
}
