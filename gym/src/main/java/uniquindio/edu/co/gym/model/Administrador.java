package uniquindio.edu.co.gym.model;

public class Administrador extends Persona {
    private String titulo;

    public Administrador(String nombre, String ID, String telefono, String direccion, String fechaNacimiento, String titulo) {
        super(nombre, ID, telefono, direccion, fechaNacimiento);
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
