package uniquindio.edu.co.gym.model;

import edu.co.gym.model.Pago;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Usario extends Persona {
    private String contraseña;
    private LocalDate fechaCreacion;
    private List<Pago> listpagos;

    public Usario(String nombre, String ID, String telefono, String direccion, String fechaNacimiento, String contraseña, LocalDate fechaCreacion) {
        super(nombre,ID,telefono,direccion,fechaNacimiento);
        this.contraseña=contraseña;
        this.fechaCreacion=fechaCreacion;
        this.listpagos=new ArrayList<>();
    }

    public List<Pago> getListpagos() {
        return listpagos;
    }

    public void setListpagos(List<Pago> listpagos) {
        this.listpagos = listpagos;
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
