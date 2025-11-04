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
    private ArrayList<TrabajadorUQ>  listTrabajadorUQ;
    private ArrayList<Estudiante> listEstudiante;
    private ArrayList<Externo> listExterno;
    private ArrayList<HistorialPago> listHistorialPago;
    private ArrayList<Recepcionista>  listRecepcionista;
    private Administrador administrador;
    private ArrayList<Entrenador> listEntrenadores;

    private Gimnasio() {
        this.nombre = "Gimnasio UQ Fit";
        this.nit = 123456;
        this.ubicacion = "Armenia, Quindío";
        this.direccion = "Cra 15 #23-45";
        this.fechaNacimiento = "2020-01-01";
        this.listMaquina = new ArrayList<>();
        this.listMembresia = new ArrayList<>();
        this.listTrabajadorUQ = new ArrayList<>();
        this.listEstudiante = new ArrayList<>();
        this.listExterno = new ArrayList<>();
        this.listHistorialPago = new ArrayList<>();
        this.listRecepcionista = new ArrayList<>();
        this.listEntrenadores = new ArrayList<>();
    }
    public void registrarMaquina(Maquina maquina) {
        this.listMaquina.add(maquina);
    }
    public void registrarMembresia(Membresia membresia) {
        this.listMembresia.add(membresia);
    }
    public void setListMaquina(ArrayList<Maquina> listMaquina) {
        this.listMaquina = listMaquina;
    }

    public void setListMembresia(ArrayList<Membresia> listMembresia) {
        this.listMembresia = listMembresia;
    }

    public void setListTrabajadorUQ(ArrayList<TrabajadorUQ> listTrabajadorUQ) {
        this.listTrabajadorUQ = listTrabajadorUQ;
    }

    public ArrayList<Estudiante> getListEstudiante() {
        return listEstudiante;
    }

    public void setListEstudiante(ArrayList<Estudiante> listEstudiante) {
        this.listEstudiante = listEstudiante;
    }

    public ArrayList<Externo> getListExterno() {
        return listExterno;
    }

    public void setListExterno(ArrayList<Externo> listExterno) {
        this.listExterno = listExterno;
    }

    public void setListHistorialPago(ArrayList<HistorialPago> listHistorialPago) {
        this.listHistorialPago = listHistorialPago;
    }

    public void setListRecepcionista(ArrayList<Recepcionista> listRecepcionista) {
        this.listRecepcionista = listRecepcionista;
    }

    public ArrayList<Entrenador> getListEntrenadores() {
        return listEntrenadores;
    }

    public void setListEntrenadores(ArrayList<Entrenador> listEntrenadores) {
        this.listEntrenadores = listEntrenadores;
    }

    public void agregarAdministrador(Administrador administrador){
        this.administrador = administrador;
    }
    public void agregarRecepcionista(Recepcionista recepcionista){
        this.listRecepcionista.add(recepcionista);
    }

    public List<TrabajadorUQ> getListTrabajadorUQ() {
        return listTrabajadorUQ;
    }


    public static Gimnasio getInstance() {
        if (instance == null) {
            instance = new Gimnasio();
        }
        return instance;
    }

    public ArrayList<Recepcionista> getListRecepcionista() {
        return listRecepcionista;
    }


    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public ArrayList<Maquina> getListMaquina() {
        return listMaquina;
    }


    public ArrayList<Membresia> getListMembresia() {
        return listMembresia;
    }


    public ArrayList<HistorialPago> getListHistorialPago() {
        return listHistorialPago;
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
