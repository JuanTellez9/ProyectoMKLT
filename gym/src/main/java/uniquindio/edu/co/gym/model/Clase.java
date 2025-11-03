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
    private List<Usuario> listUsuariosInscritos;


    public Clase(String nombre, String id,int cupoMaximo, Date fecha) {
        this.id = id;
        this.cupoMaximo = cupoMaximo;
        this.fecha = fecha;
        this.listUsuariosInscritos = new ArrayList<>();
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
}
