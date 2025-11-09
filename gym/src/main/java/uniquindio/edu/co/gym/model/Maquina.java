package uniquindio.edu.co.gym.model;

public class Maquina {
    private String nombre;
    private String id;
    private String referencia;
    private String uso;

    /**
     * Constructor de la clase Maquina.
     * Inicializa los atributos con los valores proporcionados.
     *
     * @param nombre nombre de la maquina
     * @param id identificador unico de la maquina
     * @param referencia referencia tecnica o modelo de la maquina
     * @param uso descripcion del uso principal de la maquina
     */
    public Maquina(String nombre, String id, String referencia, String uso) {
        this.nombre = nombre;
        this.id = id;
        this.referencia = referencia;
        this.uso = uso;
    }

    /**
     * Retorna el nombre de la maquina.
     *
     * @return nombre de la maquina
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna un nuevo nombre a la maquina.
     *
     * @param nombre nuevo nombre de la maquina
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Retorna el identificador de la maquina.
     *
     * @return id de la maquina
     */
    public String getId() {
        return id;
    }

    /**
     * Asigna un nuevo identificador a la maquina.
     *
     * @param id nuevo identificador
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Retorna la referencia tecnica o modelo de la maquina.
     *
     * @return referencia de la maquina
     */
    public String getReferencia() {
        return referencia;
    }

    /**
     * Asigna una nueva referencia tecnica o modelo a la maquina.
     *
     * @param referencia nueva referencia de la maquina
     */
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    /**
     * Retorna la descripcion del uso principal de la maquina.
     *
     * @return uso de la maquina
     */
    public String getUso() {
        return uso;
    }

    /**
     * Asigna una nueva descripcion de uso a la maquina.
     *
     * @param uso nuevo uso de la maquina
     */
    public void setUso(String uso) {
        this.uso = uso;
    }
}
