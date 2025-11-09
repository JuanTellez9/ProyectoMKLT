package uniquindio.edu.co.gym.model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class UsuarioLogueado implements Ihashes {
    private static UsuarioLogueado instance;
    private Persona usuario;


    /**
     * Asigna el usuario actual de la sesion.
     * @param usuario objeto Persona que representa al usuario logueado
     */
    public void setUsuario(Persona usuario) {
        this.usuario = usuario;
    }

    /**
     * Constructor privado para evitar instanciacion externa.
     */

    private UsuarioLogueado() {}

    /**
     * Retorna la unica instancia de la clase UsuarioLogueado.
     * @return instancia unica de UsuarioLogueado
     */
    public static UsuarioLogueado getInstance() {
        if (instance == null) {
            instance = new UsuarioLogueado();
        }
        return instance;
    }

    /**
     * Genera el hash de una contrasena en formato de bytes utilizando SHA-256.
     * @param contrasena texto plano de la contrasena
     * @return arreglo de bytes con el hash de la contrasena
     */
    @Override
    public byte[] hashearContrasenaBytes(String contrasena) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return digest.digest(contrasena.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al hashear la contraseña", e);
        }
    }

    /**
     * Verifica una contrasena ingresada contra un hash almacenado de forma segura.
     * @param contrasenaIngresada contrasena digitada por el usuario
     * @param hashAlmacenado arreglo de bytes del hash guardado
     * @return true si la contrasena coincide, false en caso contrario
     */
    public boolean verificarContrasenaSecure(String contrasenaIngresada, byte[] hashAlmacenado) {
        try {byte[] hashIngresado = hashearContrasenaBytes(contrasenaIngresada);
            return MessageDigest.isEqual(hashIngresado, hashAlmacenado);
        } catch (Exception e) {
            System.out.println("Error al verificar contraseña: " + e.getMessage());
            return false;
        }
    }

    /**
     * Inicia sesion en el sistema segun el tipo de usuario y credenciales proporcionadas.
     * @param id identificador del usuario
     * @param tipo tipo de usuario (administrador o recepcionista)
     * @param contrasena contrasena ingresada para autenticacion
     */
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
                            admin.getId(),
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

                if (recepcionista.getId().equals(id)) {
                    byte[] hashAlmacenado = recepcionista.getContrasena() != null
                            ? recepcionista.getContrasena().getBytes(StandardCharsets.UTF_8)
                            : new byte[0];
                    if (verificarContrasenaSecure(contrasena, hashAlmacenado)){
                        Persona persona = new Persona(
                                recepcionista.getNombre(),
                                recepcionista.getId(),
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

    /**
     * Cierra la sesion actual.
     */
    public void cerrarSesion() {
        setUsuario(null);
    }

    /**
     * Retorna el usuario actualmente logueado.
     * @return objeto Persona del usuario activo o null si no hay sesion
     */
    public Persona getUsuario() {
        return usuario;
    }

    /**
     * Verifica si existe una sesion activa.
     * @return true si hay usuario logueado, false en caso contrario
     */
    public boolean isSesionActiva() {
        return usuario != null;
    }
}
