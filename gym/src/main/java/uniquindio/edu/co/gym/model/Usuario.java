package uniquindio.edu.co.gym.model;

import edu.co.gym.model.Pago;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Usuario extends Persona {
    private LocalDate fechaCreacion;
    private ArrayList<Pago> listpagos;

    public Usuario(String nombre, String ID, String telefono, String direccion, String fechaNacimiento, LocalDate fechaCreacion) {
        super(nombre,ID,telefono,direccion,fechaNacimiento);
        this.fechaCreacion=fechaCreacion;
        this.listpagos=new ArrayList<>();

    }

    public ArrayList<Pago> getListpagos() {
        return listpagos;
    }

    public void setListpagos(List<Pago> listpagos) {
        this.listpagos = (ArrayList<Pago>) listpagos;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
