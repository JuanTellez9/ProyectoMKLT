package uniquindio.edu.co.gym.model;

import java.util.ArrayList;

public class Entrenador extends Persona {
    private String turno;
    private ArrayList<Clase> listaclasesEntrenador;

    /**
     * Constructor de la clase Entrenador.
     * Inicializa los datos personales y asigna el turno correspondiente.
     *
     * @param nombre nombre del entrenador
     * @param id identificacion del entrenador
     * @param telefono numero de contacto
     * @param direccion direccion del entrenador
     * @param fechaNacimiento fecha de nacimiento del entrenador
     * @param turno turno de trabajo asignado
     */
    public Entrenador(String nombre, String id, String telefono, String direccion, String fechaNacimiento, String turno) {
        super(nombre,id,telefono,direccion,fechaNacimiento);
        this.turno=turno;
        this.listaclasesEntrenador=new ArrayList<>();
    }
    /**
     * Metodo que permite agregar una clase a la lista de clases del entrenador.
     * Verifica que la clase no sea nula y que no este previamente asignada.
     *
     * @param clase objeto de tipo Clase que se desea agregar al entrenador
     */
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

    /**
     * Metodo que obtiene el turno de trabajo del entrenador.
     *
     * @return turno actual del entrenador
     */
    public String getTurno() {
        return turno;
    }

    /**
     * Metodo que asigna un nuevo turno al entrenador.
     *
     * @param turno nuevo turno de trabajo
     */
    public void setTurno(String turno) {
        this.turno = turno;
    }

    /**
     * Metodo que obtiene la lista de clases asignadas al entrenador.
     *
     * @return lista de objetos de tipo Clase asignadas al entrenador
     */
    public ArrayList<Clase> getListaclasesEntrenador() {return listaclasesEntrenador;}

    /**
     * Metodo que asigna una nueva lista de clases al entrenador.
     *
     * @param listaclasesEntrenador lista de objetos de tipo Clase a establecer
     */
    public void setListaclasesEntrenador (ArrayList<Clase> listaclasesEntrenador) { this.listaclasesEntrenador = listaclasesEntrenador; }
}
