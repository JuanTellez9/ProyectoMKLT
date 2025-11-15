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
    private ArrayList<Pago> listPagos;
    private ArrayList<Maquina> listMaquina;
    private ArrayList<Membresia> listMembresia;
    private ArrayList<TrabajadorUQ>  listTrabajadorUQ;
    private ArrayList<Estudiante> listEstudiante;
    private ArrayList<Externo> listExterno;
    private ArrayList<HistorialPago> listHistorialPago;
    private ArrayList<Recepcionista>  listRecepcionista;
    private Administrador administrador;
    private ArrayList<Entrenador> listEntrenadores;
    private ArrayList<Usuario> listUsuarios;
    private ArrayList<Clase> listClases;

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
        this.listClases = new ArrayList <> ();
        this.listRecepcionista = new ArrayList<>();
        this.listEntrenadores = new ArrayList<>();
        this.listUsuarios = new ArrayList<>();
        this.listPagos = new ArrayList<>();
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
    public void registrarPagos(Pago pago) {
        this.listPagos.add(pago);
    }

    public void setListMembresia(ArrayList<Membresia> listMembresia) {
        this.listMembresia = listMembresia;
    }

    public void setListTrabajadorUQ(ArrayList<TrabajadorUQ> listTrabajadorUQ) {
        this.listTrabajadorUQ = listTrabajadorUQ;
    }

    public ArrayList<Pago> getListPagos() {
        return listPagos;
    }

    public void setListPagos(ArrayList<Pago> listPagos) {
        this.listPagos = listPagos;
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

    public boolean verificarClase(Clase clase) {
        try {
            boolean bandera = false;
            for (Clase clas : listClases) {
                if (clas.getId() == clase.getId()) {
                    bandera = true;
                }
            }
            return bandera;
        } catch (Exception e) {
            System.out.println("Error al verificar la clase: " + e.getMessage());
            return false;
        }
    }

    public void registrarClase(Clase clase) {
        try {
            if (!verificarClase(clase)) {
                if (clase.getCupoMaximo() <= 0) {
                    System.out.println("⚠ No se puede registrar la clase '" + clase.getId() +
                            "': el cupo máximo debe ser mayor a 0.");
                    return;
                }

                listClases.add(clase);

                System.out.println("Clase registrada: " + clase.getId() +
                        " | Cupo máximo: " + clase.getCupoMaximo());

            } else {
                System.out.println("⚠ La clase ya existe: " + clase.getId());
            }
        } catch (Exception e) {
            System.out.println("Error al registrar la clase: " + e.getMessage());
        }
    }
    public List<Maquina> getListMaquina() {
        return listMaquina;
    }


    public ArrayList<Membresia> getListMembresia() {
        return listMembresia;
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

     public List<HistorialPago> getListHistorialPago() {
        return listHistorialPago;
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
    public void setListClases(ArrayList<Clase> listClases) {
        this.listClases = listClases;
    }

    public List<Clase> getListClases() {
        return listClases;
    }

    public void setListUsuarios(ArrayList<Usuario> listUsuarios) {
        this.listUsuarios = listUsuarios;
    }

    public ArrayList<Usuario> getListUsuarios() {
        return listUsuarios;
    }


}
