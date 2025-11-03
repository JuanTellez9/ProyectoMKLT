package uniquindio.edu.co.gym.model;

public class UsuarioLogueado {
    private static UsuarioLogueado instance;
    private Persona usuario;

    private UsuarioLogueado() {}

    public static UsuarioLogueado getInstance() {
        if (instance == null) {
            instance = new UsuarioLogueado();
        }
        return instance;
    }

    public void iniciarSesion(Persona usuario) {
        this.usuario = usuario;
        System.out.println("Sesión iniciada por: " + usuario.getNombre());
    }

    public void cerrarSesion() {
        System.out.println("Sesión cerrada de: " + (usuario != null ? usuario.getNombre() : ""));
        usuario = null;
    }

    public Persona getUsuario() {
        return usuario;
    }

    public boolean isSesionActiva() {
        return usuario != null;
    }
}
