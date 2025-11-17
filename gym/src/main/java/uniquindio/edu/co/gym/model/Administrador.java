package uniquindio.edu.co.gym.model;
import java.util.ArrayList;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//j
public class Administrador extends Persona implements Ihashes{
    private String titulo;
    private String contrasena;
    private  Gimnasio gym=Gimnasio.getInstance();


    public Administrador(String nombre, String ID, String telefono, String direccion, String fechaNacimiento, String titulo,String contrasena) {
        super(nombre, ID, telefono, direccion, fechaNacimiento);
        this.titulo = titulo;
        this.contrasena=contrasena;
    }
    public boolean verificarEntrenador(Entrenador entrenador) {
        try {
            boolean bandera = false;

            for (Entrenador ent : gym.getListEntrenadores()) {
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
                gym.registrarEntrenador(entrenador);
                System.out.println("Entrenador agregado: " + entrenador.getNombre());
            } else {
                System.out.println("El entrenador ya existe: " + entrenador.getNombre());
            }
        } catch (Exception e) {
            System.out.println("Error al registrar entrenador: " + e.getMessage());
        }
    }

    public void modificarEntrenador(String idEntrenador, String nuevoTelefono, String nuevaDireccion, String nuevoTurno) {
        try {
            boolean encontrado = false;
            ArrayList<Entrenador> arrayE=gym.getListEntrenadores();
            for (Entrenador ent : arrayE) {
                if (ent.getID().equals(idEntrenador)) {
                    ent.setTelefono(nuevoTelefono);
                    ent.setDireccion(nuevaDireccion);
                    ent.setTurno(nuevoTurno);
                    encontrado = true;
                    gym.setListEntrenadores(arrayE);
                    System.out.println(" Entrenador actualizado: " + ent.getNombre());
                    break;
                }
            }

            if (!encontrado) {
                System.out.println(" No se encontró ningún entrenador con ID: " + idEntrenador);
            }

        } catch (Exception e) {
            System.out.println("Error al modificar entrenador: " + e.getMessage());
        }
    }

    /**
     * Método que elimina un entrenador del sistema.
     */
    public void eliminarEntrenador(String idEntrenador) {
        try {
            Entrenador entrenadorAEliminar = null;
            ArrayList<Entrenador> arrayE=gym.getListEntrenadores();

            for (Entrenador ent : arrayE) {
                if (ent.getID().equals(idEntrenador)) {
                    entrenadorAEliminar = ent;
                    break;
                }
            }

            if (entrenadorAEliminar != null) {
                arrayE.remove(entrenadorAEliminar);
                gym.setListEntrenadores(arrayE);
                System.out.println(" Entrenador eliminado: " + entrenadorAEliminar.getNombre());
            } else {
                System.out.println("No se encontró el entrenador con ID: " + idEntrenador);
            }

        } catch (Exception e) {
            System.out.println("Error al eliminar entrenador: " + e.getMessage());
        }
    }

    public void asignarEntrenadorAClase(String idClase, Entrenador entrenador) {
        try {
            Clase claseEncontrada = null;
            ArrayList<Clase> arrayC=gym.getListClases();

            for (Clase c : arrayC) {
                if (c.getId().equals(idClase)) {
                    claseEncontrada = c;
                    if (!verificarEntrenador(entrenador)) {
                            throw new Exception("El entrenador no está registrado en el sistema.");
                    }

                    c.setEntrenador(entrenador);

                    entrenador.agregarClase(c);
                    gym.setListClases(arrayC);
                    System.out.println("Entrenador " + entrenador.getNombre() +
                                " asignado a la clase: " + claseEncontrada.getClaseGrupal());

                    break;
                }
            }



        } catch (Exception e) {
            System.out.println("Error al asignar entrenador: " + e.getMessage());
        }
    }

    public void generarEstadisticasAsistencia() {
        ArrayList<Clase> listClases=gym.getListClases();
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
                if (clase.getListUsario() != null) {
                    asistentes = clase.getListUsario().size();
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
            ArrayList<Pago> historialPagos=gym.getListPagos();

            if (historialPagos == null || historialPagos.isEmpty()) {
                System.out.println("No hay pagos registrados.");
                return;
            }

            double totalMembresias = 0;
            int cantidadMembresias = 0;

            for (Pago pago : historialPagos) {

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
            ArrayList<Clase> listClases=gym.getListClases();
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
                if (clase.getListUsario() != null) {
                    asistentes = clase.getListUsario().size();
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
                System.out.println("\n La clase más popular es: " + claseMasPopular.getClaseGrupal());
                System.out.println("   Con " + maxAsistentes + " asistentes.");
            } else {
                System.out.println("No se encontró ninguna clase con asistentes.");
            }

            System.out.println("---------------------------------------------");

        } catch (Exception e) {
            System.out.println("Error al generar el reporte de clases populares: " + e.getMessage());
        }
    }


    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
   

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
