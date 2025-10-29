package uniquindio.edu.co.gym.model;

import java.util.Date;

public class Clase {
    private String nombre;
    private String id;
    private int cupoMaximo;
    private Date fecha;
    private Semana semana;
    private ClaseGrupal claseGrupal;

    public Clase(String nombre, String id,int cupoMaximo, Date fecha) {
        this.nombre = nombre;
        this.id = id;

        this.cupoMaximo = cupoMaximo;
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
