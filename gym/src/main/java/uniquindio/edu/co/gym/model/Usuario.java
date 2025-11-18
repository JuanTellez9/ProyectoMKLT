package uniquindio.edu.co.gym.model;


import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Usuario extends Persona {
    private LocalDate fechaCreacion;
    private ArrayList<Pago> listpagos;
    private Membresia membresia;

    public Usuario(String nombre, String ID, String telefono, String direccion, String fechaNacimiento,LocalDate fechaCreacion) {
        super(nombre,ID,telefono,direccion,fechaNacimiento);
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

