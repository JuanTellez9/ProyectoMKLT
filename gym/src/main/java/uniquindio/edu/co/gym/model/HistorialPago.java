package uniquindio.edu.co.gym.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistorialPago {
    private String id;
    private Date fechaPago;
    private List<Usuario> listUsarios;

    public HistorialPago(String id, Date fechaPago) {
        this.id = id;
        this.fechaPago = fechaPago;
        this.listUsarios = new ArrayList<Usuario>();
    }

    public List<Usuario> getListUsarios() {
        return listUsarios;
    }

    public void setListUsarios(List<Usuario> listUsarios) {
        this.listUsarios = listUsarios;
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
}
