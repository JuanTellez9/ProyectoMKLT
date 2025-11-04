package uniquindio.edu.co.gym.model;

import edu.co.gym.model.Pago;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Usuario extends Persona {
    private String contrasena;
    private LocalDate fechaCreacion;
    private List<Pago> listpagos;
    private Membresia membresia;
    private List<Clase> listClases;

    public Usuario(String nombre, String ID, String telefono, String direccion, String fechaNacimiento, String contrasena, LocalDate fechaCreacion) {
        super(nombre,ID,telefono,direccion,fechaNacimiento);
        this.contrasena=contrasena;
        this.fechaCreacion=fechaCreacion;
        this.listpagos=new ArrayList<>();
        this.listClases = new ArrayList<>();
    }
    public abstract double calcularDescuento(Membresia membresia);

    public List<Pago> getListpagos() {
        return listpagos;
    }

    public void setListpagos(List<Pago> listpagos) {
        this.listpagos = listpagos;
    }

    public String getContraseña() {
        return contrasena;
    }
    public void setListClases(List<Clase> listClases) {
        this.listClases = listClases;
    }

    public List<Clase> getListClases() {
        return listClases;
    }
    public void setContraseña(String contraseña) {
        this.contrasena = contrasena;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    public Membresia getMembresia() {
        return membresia;
    }

    public void setMembresia(Membresia membresia) {
        this.membresia = membresia;
    }
}

