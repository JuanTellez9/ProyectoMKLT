package uniquindio.edu.co.gym.model;

public class Maquina {
    private String nombre;
    private String id;
    private String referencia;
    private String uso;

    public Maquina(String nombre, String id, String referencia, String uso) {
        this.nombre = nombre;
        this.id = id;
        this.referencia = referencia;
        this.uso = uso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getUso() {
        return uso;
    }

    public void setUso(String uso) {
        this.uso = uso;
    }
}
