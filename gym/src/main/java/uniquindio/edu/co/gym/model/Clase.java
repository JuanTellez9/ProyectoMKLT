package uniquindio.edu.co.gym.model;
import java.util.ArrayList;
import java.util.Date;

public class Clase {
    private String id;
    private int cupoMaximo;
    private Date fecha;
    private Semana semana;
    private ClaseGrupal claseGrupal;
    private ArrayList<Usuario> listUsuariosInscritos;
    private Entrenador entrenador;
    private ArrayList<Usuario> listUsuarios;

    /**
     * Constructor de la clase Clase.
     * Inicializa los atributos principales y crea la lista de usuarios inscritos.
     *
     * @param id identificador unico de la clase
     * @param cupoMaximo numero maximo de participantes permitidos
     * @param fecha fecha programada para la clase
     * @param entrenador entrenador asignado a la clase
     */
    public Clase(String id,int cupoMaximo, Date fecha, Entrenador entrenador) {
        this.id = id;
        this.cupoMaximo = cupoMaximo;
        this.fecha = fecha;
        this.listUsuariosInscritos = new ArrayList<>();
    }

    /**
     * Metodo que asigna la lista de usuarios inscritos en la clase.
     *
     * @param listUsuariosInscritos lista de objetos Usuario registrados en la clase
     */
     public void setListUsuariosInscritos(ArrayList<Usuario> listUsuariosInscritos) {
        this.listUsuariosInscritos = listUsuariosInscritos;
    }

    /**
     * Metodo que obtiene la lista de usuarios inscritos en la clase.
     *
     * @return lista de objetos Usuario inscritos
     */
    public ArrayList<Usuario> getListUsuariosInscritos() {
        return listUsuariosInscritos;
    }

    /**
     * Metodo que obtiene la semana asociada a la clase.
     *
     * @return objeto Semana correspondiente a la clase
     */
    public Semana getSemana() {
        return semana;
    }

    /**
     * Metodo que asigna la semana a la clase.
     *
     * @param semana objeto Semana que se desea asociar
     */
    public void setSemana(Semana semana) {
        this.semana = semana;
    }

    /**
     * Metodo que obtiene la clase grupal asociada.
     *
     * @return objeto ClaseGrupal vinculado a la clase
     */
    public ClaseGrupal getClaseGrupal() {
        return claseGrupal;
    }


    /**
     * Metodo que asigna una clase grupal a la clase.
     *
     * @param claseGrupal objeto ClaseGrupal a establecer
     */
    public void setClaseGrupal(ClaseGrupal claseGrupal) {
        this.claseGrupal = claseGrupal;
    }


    /**
     * Metodo que obtiene el identificador de la clase.
     *
     * @return id de la clase
     */
    public String getId() {
        return id;
    }

    /**
     * Metodo que asigna el identificador de la clase.
     *
     * @param id identificador unico a establecer
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Metodo que obtiene el cupo maximo permitido en la clase.
     *
     * @return numero maximo de participantes
     */
    public int getCupoMaximo() {
        return cupoMaximo;
    }

    /**
     * Metodo que asigna el cupo maximo de participantes en la clase.
     *
     * @param cupoMaximo cantidad maxima de usuarios permitidos
     */
    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }


    /**
     * Metodo que obtiene la fecha programada de la clase.
     *
     * @return fecha de la clase
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Metodo que asigna la fecha programada de la clase.
     *
     * @param fecha objeto Date correspondiente a la fecha de la clase
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


    /**
     * Metodo que obtiene la lista de usuarios asociados a la clase.
     *
     * @return lista de objetos Usuario registrados
     */
    public ArrayList<Usuario> getListUsuarios() {
        return listUsuarios;
    }

    /**
     * Metodo que asigna la lista de usuarios asociados a la clase.
     *
     * @param listUsuarios lista de objetos Usuario a establecer
     */
    public void setListUsuarios(ArrayList<Usuario> listUsuarios) {
        this.listUsuarios = listUsuarios;
    }

    /**
     * Metodo que obtiene el entrenador asignado a la clase.
     *
     * @return objeto Entrenador asociado
     */
    public Entrenador getEntrenador() { return entrenador; }

    /**
     * Metodo que asigna un entrenador a la clase.
     *
     * @param entrenador objeto Entrenador a establecer
     */
    public void setEntrenador(Entrenador entrenador) { this.entrenador = entrenador; }
}
