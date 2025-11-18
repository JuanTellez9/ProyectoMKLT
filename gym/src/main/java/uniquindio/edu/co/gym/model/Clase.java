package uniquindio.edu.co.gym.model;

import java.util.ArrayList;

public class Clase {

    private String id;
    private int cupoMaximo;
    private String horaInicio;
    private String horaFin;
    private Semana semana;
    private ClaseGrupal claseGrupal;
    private Entrenador entrenador;
    private ArrayList<Usuario> listUsario;

    public Clase(String id, int cupoMaximo, String horaInicio, String horaFin,
                 Semana semana, ClaseGrupal claseGrupal, Entrenador entrenador) {

        this.id = id;
        this.cupoMaximo = cupoMaximo;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.semana = semana;
        this.claseGrupal = claseGrupal;
        this.entrenador = entrenador;
        this.listUsario = new ArrayList<>();
    }

    /* ===========================
           GETTERS / SETTERS
       =========================== */

    public ArrayList<Usuario> getListUsario() {
        return listUsario;
    }

    public void setListUsario(ArrayList<Usuario> listUsario) {
        this.listUsario = listUsario;
    }

    public Semana getSemana() {
        return semana;
    }

    public void setSemana(Semana semana) {
        this.semana = semana;
    }

    public ClaseGrupal getClaseGrupal() {
        return claseGrupal;
    }

    public void setClaseGrupal(ClaseGrupal claseGrupal) {
        this.claseGrupal = claseGrupal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }


    public boolean inscribirUsuario(Usuario usuario) {

        if (usuario == null) return false;

        if (listUsario == null) {
            listUsario = new ArrayList<>();
        }

        int cupoDisponible = cupoMaximo - listUsario.size();

        if (cupoDisponible <= 0) {
            return false;
        }

        listUsario.add(usuario);
        return true;
    }

    @Override
    public String toString() {
        return id + " - " + claseGrupal + " (" + semana + ")";
    }
}
