package uniquindio.edu.co.gym.model;

public class Gimnasio {
    private String nombre;
    private int nit;
    private String ubicacion;
    private String direccion;
    private String fechaNacimiento;

    public Gimnasio(String nombre, int nit, String ubicacion, String direccion, String fechaNacimiento) {
        this.nombre = nombre;
        this.nit = nit;
        this.ubicacion = ubicacion;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNit() {
        return nit;
    }

    public void setNit(int nit) {
        this.nit = nit;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
