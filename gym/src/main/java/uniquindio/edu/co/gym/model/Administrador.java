package uniquindio.edu.co.gym.model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//j
public abstract class Administrador extends Persona implements Ihashes{
    private String titulo;
    private String contrasena;

    public Administrador(String nombre, String ID, String telefono, String direccion, String fechaNacimiento, String titulo,String contrasena) {
        super(nombre, ID, telefono, direccion, fechaNacimiento);
        this.titulo = titulo;
        this.contrasena=contrasena;
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



    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
