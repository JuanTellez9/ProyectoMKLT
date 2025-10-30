package uniquindio.edu.co.gym.model;

import java.util.ArrayList;
import java.util.List;

public class Recepcionista extends Persona {
    private String turno;
    private List<Clase> listClases;

    public Recepcionista(String nombre, String ID, String telefono, String direccion, String fechaNacimiento, String turno) {
        super(nombre, ID, telefono, direccion, fechaNacimiento);
        this.turno = turno;
        this.listClases = new ArrayList<>();
    }

    public List<Clase> getListClases() {
        return listClases;
    }

    public void setListClases(List<Clase> listClases) {
        this.listClases = listClases;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }
}
