package uniquindio.edu.co.gym.model;
import java.util.ArrayList;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//j
public abstract class Administrador extends Persona implements Ihashes{
    private String titulo;
    private String contrasena;
    private ArrayList<Entrenador> listEntrenadores;
    private ArrayList<Clase> listClases;
    private HistorialPago historialPagos;
    private ArrayList<Pago> listaPagos;

    /**
     * Constructor de la clase Administrador.
     * Inicializa los atributos basicos y las listas de entrenadores y clases.
     *
     * @param nombre nombre del administrador
     * @param id identificador del administrador
     * @param telefono numero de telefono
     * @param direccion direccion del administrador
     * @param fechaNacimiento fecha de nacimiento
     * @param titulo titulo profesional del administrador
     * @param contrasena contrasena del administrador
     */
    public Administrador(String nombre, String id, String telefono, String direccion, String fechaNacimiento, String titulo,String contrasena) {
        super(nombre, id, telefono, direccion, fechaNacimiento);
        this.titulo = titulo;
        this.contrasena=contrasena;
        this.listEntrenadores=new ArrayList();
        this.listClases=new ArrayList();
        this.historialPagos=historialPagos;
    }
    /**
     * Metodo que permite verificar si un entrenador ya esta registrado en la lista de entrenadores.
     * @param entrenador objeto de tipo Entrenador a verificar
     * @return true si el entrenador ya existe en la lista, false en caso contrario
     */
    public boolean verificarEntrenador(Entrenador entrenador) {
        try {
            boolean bandera = false;

            for (Entrenador ent : listEntrenadores) {
                if (ent.getId().equals(entrenador.getId())) {
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
    /**
     * Metodo que permite registrar un nuevo entrenador en el sistema.
     * Si el entrenador ya existe, no se agrega nuevamente.
     * @param entrenador objeto de tipo Entrenador a registrar
     */
    public void registrarEntrenador(Entrenador entrenador){
        try {
            if (!verificarEntrenador(entrenador)) {
                listEntrenadores.add(entrenador);
                System.out.println("Entrenador agregado. ");
            } else {
                System.out.println("El entrenador ya existe. ");
            }
        } catch (Exception e) {
            System.out.println("Error al registrar entrenador: " + e.getMessage());
        }
    }
    /**
     * Metodo que permite modificar los datos de un entrenador existente.
     * Actualiza telefono, dirección y turno según el ID del entrenador.
     * @param idEntrenador identificador del entrenador a modificar
     * @param nuevoTelefono nuevo número de teléfono
     * @param nuevaDireccion nueva dirección
     * @param nuevoTurno nuevo turno asignado al entrenador
     */
    public void modificarEntrenador(String idEntrenador, String nuevoTelefono, String nuevaDireccion, String nuevoTurno) {
        try {
            boolean encontrado = false;

            for (Entrenador ent : listEntrenadores) {
                if (ent.getId().equals(idEntrenador)) {
                    ent.setTelefono(nuevoTelefono);
                    ent.setDireccion(nuevaDireccion);
                    ent.setTurno(nuevoTurno);
                    encontrado = true;
                    System.out.println("Entrenador actualizado. ");
                    break;
                }
            }

            if (!encontrado) {
                System.out.println("No se encontró ningún entrenador con ID. ");
            }

        } catch (Exception e) {
            System.out.println("Error al modificar entrenador: " + e.getMessage());
        }
    }

    /**
     * Metodo que permite eliminar un entrenador del sistema segun su ID.
     * @param idEntrenador identificador del entrenador a eliminar
     */
    public void eliminarEntrenador(String idEntrenador) {
        try {
            Entrenador entrenadorAEliminar = null;

            for (Entrenador ent : listEntrenadores) {
                if (ent.getId().equals(idEntrenador)) {
                    entrenadorAEliminar = ent;
                    break;
                }
            }

            if (entrenadorAEliminar != null) {
                listEntrenadores.remove(entrenadorAEliminar);
                System.out.println("Entrenador eliminado. ");
            } else {
                System.out.println("No se encontró el entrenador con ID. ");
            }

        } catch (Exception e) {
            System.out.println("Error al eliminar entrenador: " + e.getMessage());
        }
    }

    /**
     * Metodo que permite asignar un entrenador a una clase especifica.
     * Verifica que la clase y el entrenador existan antes de asignarlos.
     * @param idClase identificador de la clase
     * @param entrenador objeto Entrenador que se asignara a la clase
     */
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
                throw new Exception("No se encontró la clase con ID. ");
            }

            if (!verificarEntrenador(entrenador)) {
                throw new Exception("El entrenador no está registrado en el sistema. ");
            }

            claseEncontrada.setEntrenador(entrenador);

            entrenador.agregarClase(claseEncontrada);
            System.out.println("Entrenador " + entrenador.getNombre() +
                    " asignado a la clase: " + claseEncontrada.getClaseGrupal());

        } catch (Exception e) {
            System.out.println("Error al asignar entrenador: " + e.getMessage());
        }
    }
    /**
     * Metodo que genera un reporte de asistencia de todas las clases registradas.
     * Muestra el cupo máximo, asistentes y el porcentaje de ocupacion por clase.
     */
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
     * Metodo que genera un reporte con el total de ingresos obtenidos por membresias.
     * Calcula la cantidad total de pagos registrados y el valor total recaudado.
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
    /**
     * Metodo que genera un reporte de las clases más populares segun el número de asistentes.
     * Identifica la clase con mayor cantidad de usuarios inscritos.
     */
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
    /**
     * Hashea una contraseña usando el algoritmo SHA-256 y devuelve el resultado en bytes.
     * @param contrasena la contrasena en texto plano que se desea hashear
     * @return arreglo de bytes con el hash SHA-256 de la contrasena
     * @throws RuntimeException si el algoritmo SHA-256 no está disponible (excepcion envuelta)
     */
    @Override
    public byte[] hashearContrasenaBytes(String contrasena) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return digest.digest(contrasena.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al hashear la contraseña", e);
        }
    }
    /**
     * Metodo que obtiene la contrasena del administrador.
     *
     * @return contrasena actual del administrador
     */
    public String getContrasena() {
        return contrasena;
    }
    /**
     * Metodo que asigna una nueva contrasena al administrador.
     *
     * @param contrasena nueva contrasena a establecer
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * Metodo que obtiene el titulo profesional del administrador.
     *
     * @return titulo actual del administrador
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Metodo que asigna un nuevo titulo profesional al administrador.
     *
     * @param titulo nuevo titulo a establecer
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
