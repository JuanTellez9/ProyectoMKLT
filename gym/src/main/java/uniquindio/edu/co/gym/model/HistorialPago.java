package uniquindio.edu.co.gym.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistorialPago {
    private String id;
    private Date fechaPago;
    private ArrayList<Usuario> listUsuarios;
    private ArrayList<Pago> listaPagos;

    public HistorialPago(String id, Date fechaPago) {
        this.id = id;
        this.fechaPago = fechaPago;
        this.listUsuarios = new ArrayList<Usuario>();
        this.listaPagos=new ArrayList<>();
    }
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
    public List<Usuario> getListUsarios() {
        return listUsuarios;
    }

    public void setListUsarios(ArrayList<Usuario> listUsarios) {
        this.listUsuarios = listUsarios;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public List<Pago> getListaPagos() {
        return listaPagos;
    }
    public void setListaPagos(ArrayList<Pago> listaPagos) {
        this.listaPagos = listaPagos;
    }
}
