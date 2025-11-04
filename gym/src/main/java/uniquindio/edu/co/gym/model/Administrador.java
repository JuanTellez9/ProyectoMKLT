package uniquindio.edu.co.gym.model;
import java.util.ArrayList;

//hh
public class Administrador extends Persona {
    private String titulo;
    private String contrasena;
    private ArrayList<Entrenador> listEntrenadores;
    private ArrayList<Clase> listClases;
    private ArrayList<Usuario> listUsuarios;
    private HistorialPago historialPagos;
    private ArrayList<Pago> listaPagos;


    public Administrador(String nombre, String ID, String telefono, String direccion, String fechaNacimiento, String titulo,String contrasena) {
        super(nombre, ID, telefono, direccion, fechaNacimiento);
        this.titulo = titulo;
        this.contrasena=contrasena;
        this.listEntrenadores=new ArrayList();
        this.listClases=new ArrayList();
        this.listUsuarios=new ArrayList();
        this.historialPagos=historialPagos;
    }
    public boolean verificarEntrenador(Entrenador entrenador) {
        try {
            boolean bandera = false;

            for (Entrenador ent : listEntrenadores) {
                if (ent.getID().equals(entrenador.getID())) {
                    bandera = true;
                    break;
                }
            }

            return bandera;

        } catch (Exception e) {
            System.out.println("Error al verificar entrenador: " + e.getMessage());
            return false;
        }

    }
    public void registrarEntrenador(Entrenador entrenador){
        try {
            if (!verificarEntrenador(entrenador)) {
                listEntrenadores.add(entrenador);
                System.out.println("Entrenador agregado: " + entrenador.getNombre());
            } else {
                System.out.println("El entrenador ya existe: " + entrenador.getNombre());
            }
        } catch (Exception e) {
            System.out.println("Error al registrar entrenador: " + e.getMessage());
        }
    }

    public void asignarEntrenadorAClase(String idClase, Entrenador entrenador) {
        try {
            Clase claseEncontrada = null;

            for (Clase c : listClases) {
                if (c.getId().equals(idClase)) {
                    claseEncontrada = c;
                    break;
                }
            }

            if (claseEncontrada == null) {
                throw new Exception("No se encontró la clase con ID: " + idClase);
            }

            if (!verificarEntrenador(entrenador)) {
                throw new Exception("El entrenador no está registrado en el sistema.");
            }

            claseEncontrada.setEntrenador(entrenador);

            entrenador.agregarClase(claseEncontrada);
            System.out.println("Entrenador " + entrenador.getNombre() +
                    " asignado a la clase: " + claseEncontrada.getClaseGrupal());

        } catch (Exception e) {
            System.out.println("Error al asignar entrenador: " + e.getMessage());
        }
    }
    public void generarEstadisticasAsistencia() {
        try {
            System.out.println("\n------ REPORTE DE ASISTENCIA ------");

            if (listClases.isEmpty()) {
                System.out.println("No hay clases registradas en el sistema.");
                return;
            }

            for (Clase clase : listClases) {
                int asistentes = 0;
                int cupoMaximo = clase.getCupoMaximo();

                // Si la clase tiene usuarios inscritos
                if (clase.getListUsuarios() != null) {
                    asistentes = clase.getListUsuarios().size();
                }

                double porcentajeOcupacion = 0;
                if (cupoMaximo > 0) {
                    porcentajeOcupacion = (asistentes * 100.0) / cupoMaximo;
                }

                System.out.println("- Clase: " + clase.getClaseGrupal());
                System.out.println("  • Cupo máximo: " + cupoMaximo);
                System.out.println("  • Asistentes: " + asistentes);
                System.out.println("  • Ocupación: " + String.format("%.2f", porcentajeOcupacion) + "%");
                System.out.println("--------------------------------------");
            }

        } catch (Exception e) {
            System.out.println("Error al generar el reporte de asistencia: " + e.getMessage());
        }
    }
    /**
     *
     * Calcula los ingresos totales por membresías registradas en el historial de pagos.
     */
    public void generarIngresosPorMembresias() {
        try {
            System.out.println("----- REPORTE DE INGRESOS POR MEMBRESÍAS -----");

            if (historialPagos == null || historialPagos.getListaPagos().isEmpty()) {
                System.out.println("No hay pagos registrados.");
                return;
            }

            double totalMembresias = 0;
            int cantidadMembresias = 0;

            for (Pago pago : historialPagos.getListaPagos()) {

                totalMembresias += pago.getValor();
                cantidadMembresias++;

            }

            System.out.println("Cantidad de pagos por membresía: " + cantidadMembresias);
            System.out.println("Total recaudado por membresías: $" + totalMembresias);
            System.out.println("----------------------------------------------\n");

        } catch (Exception e) {
            System.out.println("Error al generar reporte de ingresos por membresías: " + e.getMessage());
        }
    }
    public void generarReporteClasesMasPopulares() {
        try {
            System.out.println("\n------ REPORTE: CLASES MÁS POPULARES ------");

            if (listClases == null || listClases.isEmpty()) {
                System.out.println("No hay clases registradas en el sistema.");
                return;
            }

            // Variables para encontrar la clase más popular
            Clase claseMasPopular = null;
            int maxAsistentes = -1;

            // Recorrer todas las clases
            for (Clase clase : listClases) {
                int asistentes = 0;

                // Si la clase tiene lista de usuarios, contar los asistentes
                if (clase.getListUsuarios() != null) {
                    asistentes = clase.getListUsuarios().size();
                }

                System.out.println("- Clase: " + clase.getClaseGrupal() +
                        " | Asistentes: " + asistentes +
                        " | Cupo: " + clase.getCupoMaximo());

                // Actualizar la clase más popular
                if (asistentes > maxAsistentes) {
                    maxAsistentes = asistentes;
                    claseMasPopular = clase;
                }
            }

            // Mostrar resultado final
            if (claseMasPopular != null) {
                System.out.println("\n👉 La clase más popular es: " + claseMasPopular.getClaseGrupal());
                System.out.println("   Con " + maxAsistentes + " asistentes.");
            } else {
                System.out.println("No se encontró ninguna clase con asistentes.");
            }

            System.out.println("---------------------------------------------");

        } catch (Exception e) {
            System.out.println("Error al generar el reporte de clases populares: " + e.getMessage());
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
