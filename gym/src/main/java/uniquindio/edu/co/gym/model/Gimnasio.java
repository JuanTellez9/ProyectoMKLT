package uniquindio.edu.co.gym.model;

import java.util.ArrayList;
import java.util.List;

public class Gimnasio {
    private String nombre;
    private int nit;
    private String ubicacion;
    private String direccion;
    private String fechaNacimiento;
    private List<Maquina> listMaquina;
    private List<Membresia> listMembresia;
    private List<Persona> listPersona;
    private List<HistorialPago> listHistorialPago;

    public Gimnasio(String nombre, int nit, String ubicacion, String direccion, String fechaNacimiento) {
        this.nombre = nombre;
        this.nit = nit;
        this.ubicacion = ubicacion;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.listMaquina = new ArrayList<Maquina>();
        this.listMembresia = new ArrayList<>();
        this.listPersona = new ArrayList<>();
        this.listHistorialPago = new ArrayList<>();
    }

    public List<Maquina> getListMaquina() {
        return listMaquina;
    }

    public void setListMaquina(List<Maquina> listMaquina) {
        this.listMaquina = listMaquina;
    }

    public List<Membresia> getListMembresia() {
        return listMembresia;
    }

    public void setListMembresia(List<Membresia> listMembresia) {
        this.listMembresia = listMembresia;
    }

    public List<Persona> getListPersona() {
        return listPersona;
    }

    public void setListPersona(List<Persona> listPersona) {
        this.listPersona = listPersona;
    }

    public List<HistorialPago> getListHistorialPago() {
        return listHistorialPago;
    }

    public void setListHistorialPago(List<HistorialPago> listHistorialPago) {
        this.listHistorialPago = listHistorialPago;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNit() {
        return nit;
    }

    public void setNit(int nit) {
        this.nit = nit;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
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
