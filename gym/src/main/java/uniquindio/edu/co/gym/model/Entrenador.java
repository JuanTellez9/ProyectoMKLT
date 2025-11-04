package uniquindio.edu.co.gym.model;

import java.util.ArrayList;

public class Entrenador extends Persona {
    private String turno;
    private ArrayList<Clase> listaclasesEntrenador;

    public Entrenador(String nombre, String ID, String telefono, String direccion, String fechaNacimiento, String turno) {
        super(nombre,ID,telefono,direccion,fechaNacimiento);
        this.turno=turno;
        this.listaclasesEntrenador=new ArrayList<>();
    }
    public void agregarClase(Clase clase) {
        try {
            if (clase == null) {
                throw new Exception("La clase es nula, no se puede agregar.");
            }

            if (!listaclasesEntrenador.contains(clase)) {
                listaclasesEntrenador.add(clase);
                System.out.println("Clase agregada al entrenador: " + clase.getId());
            } else {
                System.out.println("El entrenador ya tiene esta clase asignada: " + clase.getClaseGrupal());
            }
        } catch (Exception e) {
            System.out.println("Error al agregar clase al entrenador: " + e.getMessage());
        }
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }
}
