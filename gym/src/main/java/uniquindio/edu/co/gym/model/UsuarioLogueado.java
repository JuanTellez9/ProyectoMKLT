package uniquindio.edu.co.gym.model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class UsuarioLogueado implements Ihashes {
    private static UsuarioLogueado instance;
    private Persona usuario;

    public void setUsuario(Persona usuario) {
        this.usuario = usuario;
    }

    private UsuarioLogueado() {}

    public static UsuarioLogueado getInstance() {
        if (instance == null) {
            instance = new UsuarioLogueado();
        }
        return instance;
    }
    @Override
    public byte[] hashearContrasenaBytes(String contrasena) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return digest.digest(contrasena.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al hashear la contraseña", e);
        }
    }

    public boolean verificarContrasenaSecure(String contrasenaIngresada, byte[] hashAlmacenado) {
        try {byte[] hashIngresado = hashearContrasenaBytes(contrasenaIngresada);
            return MessageDigest.isEqual(hashIngresado, hashAlmacenado);
        } catch (Exception e) {
            System.out.println("Error al verificar contraseña: " + e.getMessage());
            return false;
        }
    }

    public void iniciarSesion(String id, String tipo, String contrasena) {
        Gimnasio gym = Gimnasio.getInstance();

        if ("administrador".equalsIgnoreCase(tipo)) {
            Administrador admin = gym.getAdministrador();
            if (admin == null) {
                System.out.println("No hay administrador registrado en el sistema.");
                return;
            }

            try {
                // Asumimos que admin.getContrasena() devuelve la representación almacenada (hash en String).
                byte[] hashAlmacenado = admin.getContrasena() != null
                        ? admin.getContrasena().getBytes(StandardCharsets.UTF_8)
                        : new byte[0];

                if (verificarContrasenaSecure(contrasena, hashAlmacenado)) {
                    Persona persona = new Persona(
                            admin.getNombre(),
                            admin.getID(),
                            admin.getTelefono(),
                            admin.getDireccion(),
                            admin.getFechaNacimiento()
                    );

                    // asigna el usuario logueado (asegúrate de tener el campo 'usuario' en la clase)
                    this.usuario = persona;

                    System.out.println("Inicio de sesión exitoso: administrador " + admin.getNombre());
                } else {
                    System.out.println("Contraseña incorrecta para administrador.");
                }

            } catch (Exception e) {
                System.out.println("Error al verificar la contraseña: " + e.getMessage());
                e.printStackTrace();
            }

        } else if ("recepcionista".equalsIgnoreCase(tipo)) {

            ArrayList<Recepcionista> recepcionistas = gym.getListRecepcionista();
            if(recepcionistas.size()==0) {
                System.out.println("No hay recepcionista registrado en el sistema.");
                return;
            }
           for (Recepcionista recepcionista : recepcionistas) {

                if (recepcionista.getID().equals(id)) {
                    byte[] hashAlmacenado = recepcionista.getContrasena() != null
                            ? recepcionista.getContrasena().getBytes(StandardCharsets.UTF_8)
                            : new byte[0];
                    if (verificarContrasenaSecure(contrasena, hashAlmacenado)){
                        Persona persona = new Persona(
                                recepcionista.getNombre(),
                                recepcionista.getID(),
                                recepcionista.getTelefono(),
                                recepcionista.getDireccion(),
                                recepcionista.getFechaNacimiento()
                        );

                        // asigna el usuario logueado (asegúrate de tener el campo 'usuario' en la clase)
                        this.usuario = persona;

                        System.out.println("Inicio de sesión exitoso: administrador " + recepcionista.getNombre());
                    } else {
                        System.out.println("Contraseña incorrecta para administrador.");
                    }
                }

            }
        }
    }
    public void cerrarSesion() {
        setUsuario(null);
    }

    public Persona getUsuario() {
        return usuario;
    }

    public boolean isSesionActiva() {
        return usuario != null;
    }
}
