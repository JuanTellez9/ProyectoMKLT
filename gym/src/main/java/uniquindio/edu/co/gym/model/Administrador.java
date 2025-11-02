package uniquindio.edu.co.gym.model;
//j
public class Administrador extends Persona {
    private String titulo;
    private String contrasena;

    public Administrador(String nombre, String ID, String telefono, String direccion, String fechaNacimiento, String titulo,String contrasena) {
        super(nombre, ID, telefono, direccion, fechaNacimiento);
        this.titulo = titulo;
        this.contrasena=contrasena;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
