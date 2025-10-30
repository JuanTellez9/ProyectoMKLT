package uniquindio.edu.co.gym.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistorialPago {
    private String id;
    private Date fechaPago;
    private List<Usario> listUsarios;

    public HistorialPago(String id, Date fechaPago) {
        this.id = id;
        this.fechaPago = fechaPago;
        this.listUsarios = new ArrayList<Usario>();
    }

    public List<Usario> getListUsarios() {
        return listUsarios;
    }

    public void setListUsarios(List<Usario> listUsarios) {
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
