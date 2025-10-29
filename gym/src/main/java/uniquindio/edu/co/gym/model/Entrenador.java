package uniquindio.edu.co.gym.model;

public class Entrenador extends Persona {
    private String turno;

    public Entrenador(String nombre, String ID, String telefono, String direccion, String fechaNacimiento, String turno) {
        super(nombre,ID,telefono,direccion,fechaNacimiento);
        this.turno=turno;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }
}
