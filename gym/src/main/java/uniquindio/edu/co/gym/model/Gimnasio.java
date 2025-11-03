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
    private ArrayList<Usuario> listUsuarios;
    private ArrayList<Membresia> listMembresia;
    private ArrayList<Clase> listClases;
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
        this.listClases = new ArrayList <> ();
    }

    public static Gimnasio getInstance() {
        if (instance == null) {
            instance = new Gimnasio();
        }
        return instance;
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
