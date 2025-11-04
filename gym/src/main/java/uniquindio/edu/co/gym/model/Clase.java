package uniquindio.edu.co.gym.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Clase {
    private String id;
    private int cupoMaximo;
    private Date fecha;
    private Semana semana;
    private ClaseGrupal claseGrupal;
    private Entrenador entrenador;
    private ArrayList<Usuario> listUsuarios;


    public Clase(String id,int cupoMaximo, Date fecha, Entrenador entrenador) {
        this.id = id;

        this.cupoMaximo = cupoMaximo;
        this.fecha = fecha;
        this.entrenador = entrenador;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public ArrayList<Usuario> getListUsuarios() {
        return listUsuarios;
    }

    public void setListUsuarios(ArrayList<Usuario> listUsuarios) {
        this.listUsuarios = listUsuarios;
    }


    public Entrenador getEntrenador() { return entrenador; }
    public void setEntrenador(Entrenador entrenador) { this.entrenador = entrenador; }
}
