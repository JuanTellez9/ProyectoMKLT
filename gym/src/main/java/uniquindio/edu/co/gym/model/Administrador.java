package uniquindio.edu.co.gym.model;

import java.util.ArrayList;

public class Administrador extends Persona implements Ihashes {

    private String titulo;
    private String contrasena;
    private Gimnasio gym = Gimnasio.getInstance();

    public Administrador(String nombre, String ID, String telefono, String direccion, String fechaNacimiento, String titulo, String contrasena) {
        super(nombre, ID, telefono, direccion, fechaNacimiento);
        this.titulo = titulo;
        this.contrasena = contrasena;
    }

    /* ==========================================================
       ===============   ENTRENADORES CRUD   ====================
       ========================================================== */

    public boolean verificarEntrenador(Entrenador entrenador) {
        for (Entrenador ent : gym.getListEntrenadores()) {
            if (ent.getID().equals(entrenador.getID())) {
                return true;
            }
        }
        return false;
    }

    public void registrarEntrenador(Entrenador entrenador) {
        if (!verificarEntrenador(entrenador)) {
            gym.registrarEntrenador(entrenador);
        }
    }

    public void modificarEntrenador(String idEntrenador, String nuevoTelefono, String nuevaDireccion, String nuevoTurno) {
        ArrayList<Entrenador> lista = gym.getListEntrenadores();

        for (Entrenador ent : lista) {
            if (ent.getID().equals(idEntrenador)) {
                ent.setTelefono(nuevoTelefono);
                ent.setDireccion(nuevaDireccion);
                ent.setTurno(nuevoTurno);
                gym.setListEntrenadores(lista);
                return;
            }
        }
    }

    public void eliminarEntrenador(String idEntrenador) {
        ArrayList<Entrenador> lista = gym.getListEntrenadores();
        Entrenador eliminar = null;

        for (Entrenador e : lista) {
            if (e.getID().equals(idEntrenador)) {
                eliminar = e;
                break;
            }
        }

        if (eliminar != null) {
            lista.remove(eliminar);
            gym.setListEntrenadores(lista);
        }
    }

    /* ==========================================================
       ===============   REPORTES CON RETORNO   =================
       ========================================================== */

    /** Clase Auxiliar Reporte Asistencia **/
    public static class ReporteAsistencia {
        private String clase, cupo, asistentes, ocupacion;

        public ReporteAsistencia(String clase, String cupo, String asistentes, String ocupacion) {
            this.clase = clase;
            this.cupo = cupo;
            this.asistentes = asistentes;
            this.ocupacion = ocupacion;
        }

        public String getClase() { return clase; }
        public String getCupo() { return cupo; }
        public String getAsistentes() { return asistentes; }
        public String getOcupacion() { return ocupacion; }
    }

    /** Retorna lista formateada para tabla Asistencia */
    public ArrayList<ReporteAsistencia> generarEstadisticasAsistencia() {
        ArrayList<ReporteAsistencia> result = new ArrayList<>();

        for (Clase clase : gym.getListClases()) {

            int asistentes = (clase.getListUsario() != null)
                    ? clase.getListUsario().size()
                    : 0;

            int cupo = clase.getCupoMaximo();

            double porc = (cupo > 0)
                    ? (asistentes * 100.0 / cupo)
                    : 0;

            result.add(new ReporteAsistencia(
                    clase.getClaseGrupal().toString(),
                    String.valueOf(cupo),
                    String.valueOf(asistentes),
                    String.format("%.2f%%", porc)
            ));
        }
        return result;
    }


    /** Clase Auxiliar Reporte Ingresos */
    public static class ReporteIngresos {
        private String cantidad, total;

        public ReporteIngresos(String cantidad, String total) {
            this.cantidad = cantidad;
            this.total = total;
        }

        public String getCantidad() { return cantidad; }
        public String getTotal() { return total; }
    }

    public ReporteIngresos generarIngresosPorMembresias() {
        ArrayList<Pago> pagos = gym.getListPagos();

        if (pagos == null || pagos.isEmpty()) {
            return new ReporteIngresos("0", "$0");
        }

        int cantidad = pagos.size();
        double total = pagos.stream().mapToDouble(Pago::getValor).sum();

        return new ReporteIngresos(
                String.valueOf(cantidad),
                "$" + total
        );
    }


    /** Reporte clases ordenadas por popularidad */
    public ArrayList<Clase> generarReporteClasesMasPopulares() {
        ArrayList<Clase> result = new ArrayList<>(gym.getListClases());

        result.sort((a, b) -> {
            int asA = (a.getListUsario() != null) ? a.getListUsario().size() : 0;
            int asB = (b.getListUsario() != null) ? b.getListUsario().size() : 0;
            return Integer.compare(asB, asA);
        });

        return result;
    }

    /* GETTERS Y SETTERS */
    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
}
