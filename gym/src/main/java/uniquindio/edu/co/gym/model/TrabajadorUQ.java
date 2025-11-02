package uniquindio.edu.co.gym.model;

import java.time.LocalDate;

public class TrabajadorUQ extends Usuario {

    private String cargo;
    private String area;
    private Beneficio beneficio;

    public TrabajadorUQ(String nombre, String ID, String telefono, String direccion, String fechaNacimiento, String contraseña, LocalDate fechaCreacion, String cargo, String area) {
        super(nombre,ID,telefono,direccion,fechaNacimiento,contraseña,fechaCreacion);
        this.cargo = cargo;
    }

    public Beneficio getBeneficio() {
        return beneficio;
    }

    public void setBeneficio(Beneficio beneficio) {
        this.beneficio = beneficio;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
