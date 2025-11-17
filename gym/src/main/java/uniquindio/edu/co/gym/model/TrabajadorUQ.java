package uniquindio.edu.co.gym.model;

import java.time.LocalDate;

public class TrabajadorUQ extends Usuario {

    private String cargo;
    private String area;
    private Beneficio beneficio;

    public TrabajadorUQ(String nombre, String ID, String telefono, String direccion, String fechaNacimiento, LocalDate fechaCreacion, String cargo, String area) {
        super(nombre,ID,telefono,direccion,fechaNacimiento,fechaCreacion);
        this.cargo = cargo;
        this.area=area;
    }

    @Override
    public double calcularDescuento(Membresia membresia) {
        Membresia membre = (membresia != null) ? membresia : this.getMembresia();
        if (membre == null) {
            return 0;
        }
        double valorConDescuento = membre.getCosto() - (membre.getCosto() * 0.15);
        return valorConDescuento;
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
