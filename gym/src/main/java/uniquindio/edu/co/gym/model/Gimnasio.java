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
    private ArrayList<TrabajadorUQ> listTrabajadorUQ;
    private ArrayList<Estudiante> listEstudiante;
    private ArrayList<Externo> listExterno;
    private ArrayList<HistorialPago> listHistorialPago;
    private ArrayList<Recepcionista> listRecepcionista;
    private Administrador administrador;
    private ArrayList<Entrenador> listEntrenadores;
    private ArrayList<Usuario> listUsuarios;
    private ArrayList<Clase> listClases;

    /**
     * Constructor privado de la clase Gimnasio.
     * Inicializa los atributos del gimnasio con valores por defecto
     * y crea las listas vacias correspondientes.
     */
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
        this.listClases = new ArrayList<>();
        this.listRecepcionista = new ArrayList<>();
        this.listEntrenadores = new ArrayList<>();
    }


    /**
     * Metodo que permite registrar una nueva maquina en el gimnasio.
     *
     * @param maquina objeto de tipo Maquina que se desea registrar
     */
    public void registrarMaquina(Maquina maquina) {
        this.listMaquina.add(maquina);
    }

    /**
     * Metodo que permite registrar una nueva membresia en el gimnasio.
     *
     * @param membresia objeto de tipo Membresia que se desea registrar
     */
    public void registrarMembresia(Membresia membresia) {
        this.listMembresia.add(membresia);
    }

    /**
     * Metodo que establece la lista de maquinas del gimnasio.
     *
     * @param listMaquina lista de maquinas a asignar
     */
    public void setListMaquina(ArrayList<Maquina> listMaquina) {
        this.listMaquina = listMaquina;
    }


    /**
     * Metodo que establece la lista de membresias del gimnasio.
     *
     * @param listMembresia lista de membresias a asignar
     */
    public void setListMembresia(ArrayList<Membresia> listMembresia) {
        this.listMembresia = listMembresia;
    }

    /**
     * Metodo que establece la lista de trabajadores UQ.
     *
     * @param listTrabajadorUQ lista de objetos TrabajadorUQ a asignar
     */
    public void setListTrabajadorUQ(ArrayList<TrabajadorUQ> listTrabajadorUQ) {
        this.listTrabajadorUQ = listTrabajadorUQ;
    }

    /**
     * Metodo que obtiene la lista de estudiantes registrados.
     *
     * @return lista de objetos Estudiante
     */
    public ArrayList<Estudiante> getListEstudiante() {
        return listEstudiante;
    }


    /**
     * Metodo que asigna una lista de estudiantes.
     *
     * @param listEstudiante lista de estudiantes a establecer
     */
    public void setListEstudiante(ArrayList<Estudiante> listEstudiante) {
        this.listEstudiante = listEstudiante;
    }

    /**
     * Metodo que obtiene la lista de usuarios externos.
     *
     * @return lista de objetos Externo
     */
    public ArrayList<Externo> getListExterno() {
        return listExterno;
    }

    /**
     * Metodo que asigna una lista de usuarios externos.
     *
     * @param listExterno lista de objetos Externo a establecer
     */
    public void setListExterno(ArrayList<Externo> listExterno) {
        this.listExterno = listExterno;
    }

    /**
     * Metodo que asigna la lista de historiales de pago del gimnasio.
     *
     * @param listHistorialPago lista de objetos HistorialPago a establecer
     */
    public void setListHistorialPago(ArrayList<HistorialPago> listHistorialPago) {
        this.listHistorialPago = listHistorialPago;
    }

    /**
     * Metodo que asigna la lista de recepcionistas.
     *
     * @param listRecepcionista lista de objetos Recepcionista a establecer
     */
    public void setListRecepcionista(ArrayList<Recepcionista> listRecepcionista) {
        this.listRecepcionista = listRecepcionista;
    }

    /**
     * Metodo que obtiene la lista de entrenadores.
     *
     * @return lista de objetos Entrenador
     */
    public ArrayList<Entrenador> getListEntrenadores() {
        return listEntrenadores;
    }

    /**
     * Metodo que asigna la lista de entrenadores del gimnasio.
     *
     * @param listEntrenadores lista de objetos Entrenador a establecer
     */
    public void setListEntrenadores(ArrayList<Entrenador> listEntrenadores) {
        this.listEntrenadores = listEntrenadores;
    }

    /**
     * Metodo que agrega un administrador al gimnasio.
     *
     * @param administrador objeto de tipo Administrador a agregar
     */
    public void agregarAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    /**
     * Metodo que agrega un recepcionista al gimnasio.
     *
     * @param recepcionista objeto de tipo Recepcionista a agregar
     */
    public void agregarRecepcionista(Recepcionista recepcionista) {
        this.listRecepcionista.add(recepcionista);
    }

    /**
     * Metodo que obtiene la lista de trabajadores UQ.
     *
     * @return lista de objetos TrabajadorUQ
     */
    public List<TrabajadorUQ> getListTrabajadorUQ() {
        return listTrabajadorUQ;
    }

    /**
     * Metodo que retorna la unica instancia de la clase Gimnasio (patron Singleton).
     *
     * @return instancia unica de Gimnasio
     */
    public static Gimnasio getInstance() {
        if (instance == null) {
            instance = new Gimnasio();
        }
        return instance;
    }

    /**
     * Metodo que obtiene la lista de recepcionistas.
     *
     * @return lista de objetos Recepcionista
     */
    public ArrayList<Recepcionista> getListRecepcionista() {
        return listRecepcionista;
    }

    /**
     * Metodo que obtiene el administrador del gimnasio.
     *
     * @return objeto de tipo Administrador
     */
    public Administrador getAdministrador() {
        return administrador;
    }

    /**
     * Metodo que asigna el administrador del gimnasio.
     *
     * @param administrador objeto de tipo Administrador a establecer
     */
    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    /**
     * Metodo que verifica si una clase ya existe en la lista del gimnasio.
     *
     * @param clase objeto de tipo Clase a verificar
     * @return true si la clase ya existe, false en caso contrario
     */
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

    /**
     * Metodo que registra una nueva clase en el gimnasio si no existe previamente
     * y valida que el cupo maximo sea mayor a cero.
     *
     * @param clase objeto de tipo Clase a registrar
     */
    public void registrarClase(Clase clase) {
        try {
            if (!verificarClase(clase)) {
                if (clase.getCupoMaximo() <= 0) {
                    System.out.println("No se puede registrar la clase '" + clase.getId() +
                            "': el cupo máximo debe ser mayor a 0.");
                    return;
                }

                listClases.add(clase);

                System.out.println("Clase registrada: " + clase.getId() +
                        " y Cupo máximo: " + clase.getCupoMaximo());

            } else {
                System.out.println("La clase ya existe: " + clase.getId());
            }
        } catch (Exception e) {
            System.out.println("Error al registrar la clase: " + e.getMessage());
        }
    }

    /**
     * Metodo que obtiene la lista de maquinas del gimnasio.
     *
     * @return lista de objetos Maquina
     */
    public List<Maquina> getListMaquina() {
        return listMaquina;
    }

    /**
     * Metodo que obtiene la lista de membresias disponibles.
     *
     * @return lista de objetos Membresia
     */
    public ArrayList<Membresia> getListMembresia() {
        return listMembresia;
    }

    /**
     * Metodo que obtiene el nombre del gimnasio.
     *
     * @return nombre del gimnasio
     */
    public String getNombre() {
        return nombre;
    }


    /**
     * Metodo que asigna el nombre del gimnasio.
     *
     * @param nombre nombre a establecer
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo que obtiene el NIT del gimnasio.
     *
     * @return numero NIT
     */
    public int getNit() {
        return nit;
    }

    /**
     * Metodo que asigna el NIT del gimnasio.
     *
     * @param nit numero NIT a establecer
     */
    public void setNit(int nit) {
        this.nit = nit;
    }

    /**
     * Metodo que obtiene la ubicacion del gimnasio.
     *
     * @return ubicacion del gimnasio
     */
    public String getUbicacion() {
        return ubicacion;
    }

    /**
     * Metodo que asigna la ubicacion del gimnasio.
     *
     * @param ubicacion ubicacion a establecer
     */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    /**
     * Metodo que obtiene la direccion del gimnasio.
     *
     * @return direccion del gimnasio
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Metodo que obtiene la lista de historiales de pago.
     *
     * @return lista de objetos HistorialPago
     */
    public List<HistorialPago> getListHistorialPago() {
        return listHistorialPago;
    }

    /**
     * Metodo que asigna la direccion del gimnasio.
     *
     * @param direccion direccion a establecer
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Metodo que obtiene la fecha de nacimiento o fundacion del gimnasio.
     *
     * @return fecha en formato String
     */
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Metodo que asigna la fecha de nacimiento o fundacion del gimnasio.
     *
     * @param fechaNacimiento fecha en formato String
     */
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Metodo que establece la lista de clases disponibles en el gimnasio.
     *
     * @param listClases lista de objetos Clase a asignar
     */
    public void setListClases(ArrayList<Clase> listClases) {
        this.listClases = listClases;
    }

    /**
     * Metodo que obtiene la lista de clases registradas en el gimnasio.
     *
     * @return lista de objetos Clase
     */
    public List<Clase> getListClases() {
        return listClases;
    }


    /**
     * Metodo que asigna la lista de usuarios registrados en el gimnasio.
     *
     * @param listUsuarios lista de objetos Usuario a asignar
     */
    public void setListUsuarios(ArrayList<Usuario> listUsuarios) {
        this.listUsuarios = listUsuarios;
    }

    /**
     * Metodo que obtiene la lista de usuarios registrados en el gimnasio.
     *
     * @return lista de objetos Usuario
     */
    public ArrayList<Usuario> getListUsuarios() {
        return listUsuarios;
    }
}
