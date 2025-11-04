package uniquindio.edu.co.gym.model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Recepcionista extends Persona implements Ihashes {
    private String turno;
    private String contrasena;
    private ArrayList<Clase> listClases;
    private ArrayList<Usuario> listUsario;

    public Recepcionista(String nombre, String ID, String telefono, String direccion, String fechaNacimiento, String turno,String contrasena) {
        super(nombre, ID, telefono, direccion, fechaNacimiento);
        this.turno = turno;
        this.contrasena= Arrays.toString(hashearContrasenaBytes(contrasena));
        this.listClases = new ArrayList<>();
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

    public List<uniquindio.edu.co.gym.model.Usuario> getListUsario() {
        return listUsario;
    }

    public void setListClases(ArrayList<Clase> listClases) {
        this.listClases = listClases;
    }

    public void setListUsario(ArrayList<Usuario> listUsario) {
        this.listUsario = listUsario;
    }

    public List<Clase> getListClases() {
        return listClases;
    }


    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }
}
