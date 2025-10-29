package uniquindio.edu.co.gym.model;

import java.util.Date;

public class HistorialPago {
    private String id;
    private Date fechaPago;

    public HistorialPago(String id, Date fechaPago) {
        this.id = id;
        this.fechaPago = fechaPago;
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
