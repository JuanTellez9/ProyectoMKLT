package uniquindio.edu.co.gym.model;

import java.util.ArrayList;
import java.util.List;

public class Persona {
    private String nombre;
    private String id;
    private String telefono;
    private String direccion;
    private String fechaNacimiento;
    private ArrayList<Usuario> listUsuarios;
    private String foto;

    /**
     * Constructor de la clase Persona.
     * Inicializa los valores de nombre, id, telefono, direccion y fecha de nacimiento.
     * @param nombre Nombre de la persona.
     * @param id Identificador unico de la persona.
     * @param telefono Numero de telefono de la persona.
     * @param direccion Direccion de residencia de la persona.
     * @param fechaNacimiento Fecha de nacimiento de la persona.
     */
    public Persona(String nombre, String id, String telefono, String direccion, String fechaNacimiento) {
        this.nombre = nombre;
        this.id = id;
        this.telefono = telefono;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.listUsuarios = new ArrayList<>();
        this.foto = "";
    }

    /**
     * Obtiene la lista de usuarios asociados a la persona.
     * @return Lista de objetos Usuario.
     */
    public List<Usuario> getListUsarios() {
        return listUsuarios;
    }

    /**
     * Asigna una nueva lista de usuarios a la persona.
     * @param listUsuarios Lista de objetos Usuario.
     */
    public void setListUsuarios(ArrayList<Usuario> listUsuarios) {
        this.listUsuarios = listUsuarios;
    }

    /**
     * Obtiene la ruta o referencia de la foto de la persona.
     * @return Foto en formato String.
     */
    public String getFoto() {
        return foto;
    }

    /**
     * Asigna la foto de la persona.
     * @param foto Ruta o referencia de la foto.
     */
    public void setFoto(String foto) {
        this.foto = foto;
    }

    /**
     * Obtiene el nombre de la persona.
     * @return Nombre en formato String.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna un nuevo nombre a la persona.
     * @param nombre Nombre de la persona.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el id de la persona.
     * @return Id en formato String.
     */
    public String getId() {
        return id;
    }

    /**
     * Asigna un nuevo id a la persona.
     * @param id Identificador unico de la persona.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el telefono de la persona.
     * @return Numero de telefono en formato String.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Asigna un nuevo telefono a la persona.
     * @param telefono Numero de telefono de la persona.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Obtiene la direccion de la persona.
     * @return Direccion en formato String.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Asigna una nueva direccion a la persona.
     * @param direccion Direccion de residencia de la persona.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Obtiene la fecha de nacimiento de la persona.
     * @return Fecha de nacimiento en formato String.
     */
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Asigna una nueva fecha de nacimiento a la persona.
     * @param fechaNacimiento Fecha de nacimiento en formato String.
     */
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }



}
