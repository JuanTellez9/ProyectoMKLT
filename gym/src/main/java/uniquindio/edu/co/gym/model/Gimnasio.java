package uniquindio.edu.co.gym.model;

import java.util.ArrayList;
import java.util.List;

public class Gimnasio {
    private static Gimnasio instance;
    private String nombre;
    private int nit;
    private String ubicacion;
    private String direccion;
    private String fechaNacimiento;
    private ArrayList<Maquina> listMaquina;
    private ArrayList<Membresia> listMembresia;
    private ArrayList<Persona> listPersona;
    private ArrayList<HistorialPago> listHistorialPago;

    private Gimnasio() {
        this.nombre = "Gimnasio UQ Fit";
        this.nit = 123456;
        this.ubicacion = "Armenia, Quindío";
        this.direccion = "Cra 15 #23-45";
        this.fechaNacimiento = "2020-01-01";
        this.listMaquina = new ArrayList<>();
        this.listMembresia = new ArrayList<>();
        this.listPersona = new ArrayList<>();
        this.listHistorialPago = new ArrayList<>();
    }

    public static Gimnasio getInstance() {
        if (instance == null) {
            instance = new Gimnasio();
        }
        return instance;
    }


    public List<Maquina> getListMaquina() {
        return listMaquina;
    }

    public void setListMaquina(ArrayList<Maquina> listMaquina) {
        this.listMaquina = listMaquina;
    }

    public List<Membresia> getListMembresia() {
        return listMembresia;
    }

    public void setListMembresia(ArrayList<Membresia> listMembresia) {
        this.listMembresia = listMembresia;
    }

    public List<Persona> getListPersona() {
        return listPersona;
    }

    public void setListPersona(ArrayList<Persona> listPersona) {
        this.listPersona = listPersona;
    }

    public List<HistorialPago> getListHistorialPago() {
        return listHistorialPago;
    }

    public void setListHistorialPago(ArrayList<HistorialPago> listHistorialPago) {
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
