package uniquindio.edu.co.gym.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistorialPago {
    private String id;
    private Date fechaPago;
    private ArrayList<Usuario> listUsuarios;
    private ArrayList<Pago> listaPagos;

    /**
     * Constructor de la clase HistorialPago.
     * Inicializa las listas de usuarios y pagos asociados al historial.
     *
     * @param id identificador del historial de pago
     * @param fechaPago fecha en la que se registro el pago
     */
    public HistorialPago(String id, Date fechaPago) {
        this.id = id;
        this.fechaPago = fechaPago;
        this.listUsuarios = new ArrayList<Usuario>();
        this.listaPagos=new ArrayList<>();
    }

    /**
     * Metodo que registra un pago realizado por un usuario.
     * Si el usuario o el pago son nulos, lanza una excepcion.
     *
     * @param usuario objeto de tipo Usuario que realizo el pago
     * @param pago objeto de tipo Pago con la informacion del pago
     */
    public void registrarPago(Usuario usuario, Pago pago) {
        try {
            if (usuario == null || pago == null) {
                throw new Exception("Usuario o pago no pueden ser nulos.");
            }

            listUsuarios.add(usuario);
            listaPagos.add(pago);

            System.out.println("Pago registrado correctamente:");
            System.out.println("Usuario: " + usuario.getNombre());
            System.out.println("Monto: $" + pago.getValor());
            System.out.println("Fecha: " + pago.getFechaPago());

        } catch (Exception e) {
            System.out.println("Error al registrar pago: " + e.getMessage());
        }
    }

    /**
     * Retorna la lista de usuarios asociados al historial de pagos.
     *
     * @return lista de usuarios registrados en el historial
     */
    public List<Usuario> getListUsarios() {
        return listUsuarios;
    }

    /**
     * Asigna una nueva lista de usuarios al historial de pagos.
     *
     * @param listUsarios lista de usuarios a asignar
     */
    public void setListUsarios(ArrayList<Usuario> listUsarios) {
        this.listUsuarios = listUsarios;
    }

    /**
     * Retorna el identificador del historial de pago.
     *
     * @return id del historial
     */
    public String getId() {
        return id;
    }

    /**
     * Asigna un nuevo identificador al historial de pago.
     *
     * @param id nuevo identificador
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Retorna la fecha en la que se realizo el pago.
     *
     * @return fecha del pago
     */
    public Date getFechaPago() {
        return fechaPago;
    }

    /**
     * Asigna una nueva fecha al historial de pago.
     *
     * @param fechaPago nueva fecha de pago
     */
    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    /**
     * Retorna la lista de pagos registrados en el historial.
     *
     * @return lista de objetos Pago
     */
    public List<Pago> getListaPagos() {
        return listaPagos;
    }

    /**
     * Asigna una nueva lista de pagos al historial.
     *
     * @param listaPagos lista de pagos a asignar
     */
    public void setListaPagos(ArrayList<Pago> listaPagos) {
        this.listaPagos = listaPagos;
    }
}
