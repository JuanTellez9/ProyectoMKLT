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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    private byte[] hexToBytes(String hex) {
        int len = hex.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i+1), 16));
        }
        return data;
    }


    public boolean verificarContrasenaSecure(String contrasenaIngresada, String hashAlmacenadoHex) {
        if (hashAlmacenadoHex == null || hashAlmacenadoHex.isEmpty()) {
            return false;
        }
        byte[] hashIngresado = hashearContrasenaBytes(contrasenaIngresada);
        byte[] hashAlmacenado = hexToBytes(hashAlmacenadoHex);

        return MessageDigest.isEqual(hashIngresado, hashAlmacenado);
    }


    public void iniciarSesion(String id, String tipo, String contrasena) {

        Gimnasio gym = Gimnasio.getInstance();

        // === ADMINISTRADOR ===
        if ("administrador".equalsIgnoreCase(tipo)) {

            Administrador admin = gym.getAdministrador();
            if (admin == null) {
                System.out.println("No hay administrador registrado");
                return;
            }

            if (!admin.getID().equals(id)) {
                System.out.println("ID incorrecto");
                return;
            }

            if (!verificarContrasenaSecure(contrasena, admin.getContrasena())) {
                System.out.println("Contraseña incorrecta");
                return;
            }

            this.usuario = new Persona(
                    admin.getNombre(),
                    admin.getID(),
                    admin.getTelefono(),
                    admin.getDireccion(),
                    admin.getFechaNacimiento()
            );

            System.out.println("Inicio de sesión exitoso (ADMIN)");
            return;
        }

        // === RECEPCIONISTA ===
        if ("recepcionista".equalsIgnoreCase(tipo)) {

            for (Recepcionista r : gym.getListRecepcionista()) {

                if (r.getID().equals(id)) {

                    if (!verificarContrasenaSecure(contrasena, r.getContrasena())) {
                        System.out.println("Contraseña incorrecta");
                        return;
                    }

                    this.usuario = new Persona(
                            r.getNombre(),
                            r.getID(),
                            r.getTelefono(),
                            r.getDireccion(),
                            r.getFechaNacimiento()
                    );

                    System.out.println("Inicio de sesión exitoso (RECEPCIONISTA)");
                    return;
                }
            }

            System.out.println("Recepcionista no encontrado.");
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
