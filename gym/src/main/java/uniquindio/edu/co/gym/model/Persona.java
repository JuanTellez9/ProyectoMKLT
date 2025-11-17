package uniquindio.edu.co.gym.model;

import java.util.ArrayList;
import java.util.List;

public class Persona {
    private String nombre;
    private String ID;
    private String telefono;
    private String direccion;
    private String fechaNacimiento;
    private ArrayList<Usuario> listUsarios;
    private String foto;

    public Persona(String nombre, String ID, String telefono, String direccion, String fechaNacimiento) {
        this.nombre = nombre;
        this.ID = ID;
        this.telefono = telefono;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.listUsarios = new ArrayList<>();
        this.foto = "https://www.shutterstock.com/image-vector/default-avatar-profile-icon-vector-600nw-1725655669.jpg";
    }

    public List<Usuario> getListUsarios() {
        return listUsarios;
    }

    public void setListUsarios(ArrayList<Usuario> listUsarios) {
        this.listUsarios = listUsarios;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }



}
