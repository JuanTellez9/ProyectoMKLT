package uniquindio.edu.co.gym.model;

import java.time.LocalDate;

public class Externo extends Usuario {
    private String ocupacion;

    public Externo (String nombre, String ID, String telefono, String direccion, String fechaNacimiento, LocalDate fechaCreacion, String ocupacion) {
        super(nombre,ID,telefono,direccion,fechaNacimiento,fechaCreacion);
        this.ocupacion = ocupacion;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }
}
