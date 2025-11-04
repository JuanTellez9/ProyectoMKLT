package uniquindio.edu.co.gym.model;


import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Usuario extends Persona {
    private String contrasena;
    private LocalDate fechaCreacion;
    private ArrayList<Pago> listpagos;
    private ArrayList<Clase> listClases;
    private Membresia membresia;

    public Usuario(String nombre, String ID, String telefono, String direccion, String fechaNacimiento, String contrasena, LocalDate fechaCreacion) {
        super(nombre,ID,telefono,direccion,fechaNacimiento);
        this.contrasena=contrasena;
        this.fechaCreacion=fechaCreacion;
        this.listpagos=new ArrayList<>();
    }
    public abstract double calcularDescuento(Membresia membresia);

    public ArrayList<Pago> getListpagos() {
        return listpagos;
    }

    public void setListpagos(ArrayList<Pago> listpagos) {
        this.listpagos = listpagos;
    }

    public String getContrasena() {
        return contrasena;
    }
    public void setListClases(ArrayList<Clase> listClases) {
        this.listClases = listClases;
    }

    public ArrayList<Clase> getListClases() {
        return listClases;
    }
    public void setContrasena(String contrasena) {
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

