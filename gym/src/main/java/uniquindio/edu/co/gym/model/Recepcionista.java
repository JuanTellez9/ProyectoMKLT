package uniquindio.edu.co.gym.model;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public  class Recepcionista extends Persona implements Ihashes {
    private String turno;
    private String contrasena;
    private ArrayList<Clase> listClases;
    private ArrayList<Membresia> listMembresias;
    private ArrayList<Usuario> listUsuarios;

    /**
     * Constructor de la clase Recepcionista.
     * Inicializa los valores del recepcionista y aplica un hash a la contrasena.
     * @param nombre Nombre del recepcionista.
     * @param id Identificador unico del recepcionista.
     * @param telefono Numero de telefono del recepcionista.
     * @param direccion Direccion del recepcionista.
     * @param fechaNacimiento Fecha de nacimiento del recepcionista.
     * @param turno Turno de trabajo del recepcionista.
     * @param contrasena Contrasena del recepcionista (se almacena de forma hasheada).
     */
    public Recepcionista(String nombre, String id, String telefono, String direccion, String fechaNacimiento, String turno,String contrasena) {
        super(nombre, id, telefono, direccion, fechaNacimiento);
        this.turno = turno;
        this.contrasena= Arrays.toString(hashearContrasenaBytes(contrasena));
        this.listClases = new ArrayList<>();
        this.listMembresias = new ArrayList<>();
        this.listUsuarios = new ArrayList<>();
    }

    /**
     * Verifica si un usuario ya esta registrado en la lista del recepcionista.
     * @param usuario Usuario a verificar.
     * @return true si el usuario ya existe, false en caso contrario.
     */
    public boolean verificarUsuario(Usuario usuario){
        boolean bandera = false;
        for(Usuario usu : listUsuarios){
            if(usu.getId() == usuario.getId()){
                bandera = true;
            }
        }
        return bandera;
    }

    /**
     * Registra un nuevo usuario si no existe previamente.
     * @param usuario Usuario a registrar.
     */
    public void registrarUsuario(Usuario usuario) {
        if (!verificarUsuario(usuario)) {
            listUsuarios.add(usuario);
            System.out.println("Usuario registrado: " + usuario.getNombre());
        } else {
            System.out.println("El usuario ya existe" + usuario.getNombre());
        }
    }

    /**
     * Permite al recepcionista reservar una clase para un usuario dentro del gimnasio.
     * @param gimnasio Objeto Gimnasio donde se realiza la reserva.
     * @param usuario Usuario que desea reservar la clase.
     * @param clase Clase a reservar.
     */
    public void reservarClase(Gimnasio gimnasio, Usuario usuario, Clase clase) {
        try {
            if (gimnasio == null || usuario == null || clase == null) {
                System.out.println("Error: datos inválidos (usuario, clase o gimnasio nulo).");
                return;
            }

            boolean encontrada = false;
            Clase claseEncontrada = null;

            for (Clase c : gimnasio.getListClases()) {
                if (c.getId().equals(clase.getId())) {
                    encontrada = true;
                    claseEncontrada = c;
                }
            }

            if (!encontrada) {
                System.out.println("Error: la clase '" + clase.getId() + "' no está registrada en el gimnasio.");
                return;
            }

            if (claseEncontrada.getCupoMaximo() <= 0) {
                System.out.println("Error: no hay cupos disponibles para la clase '" + clase.getId() + "'.");
                return;
            }

            if (usuario.getListClases().contains(claseEncontrada)) {
                System.out.println("El usuario '" + usuario.getNombre() + "' ya tiene reservada la clase '"
                        + clase.getId() + "'.");
                return;
            }

            usuario.getListClases().add(claseEncontrada);
            claseEncontrada.setCupoMaximo(claseEncontrada.getCupoMaximo() - 1);

            System.out.println("Clase '" + claseEncontrada.getId() + "' reservada exitosamente para "
                    + usuario.getNombre() + ". Cupos restantes: " + claseEncontrada.getCupoMaximo());

        } catch (Exception e) {
            System.out.println("Error al reservar la clase: " + e.getMessage());
        }
    }

    /**
     * Asigna una membresia a un usuario.
     * @param usuario Usuario al que se le asigna la membresia.
     * @param membresia Membresia a asignar.
     */
    public void asignarMembresia(Usuario usuario, Membresia membresia) {
        try {
            if (usuario == null || membresia == null) {
                System.out.println("Error: el usuario o la membresía son nulos.");
                return;
            }

            usuario.setMembresia(membresia);
            System.out.println("Membresía " + membresia.getTipo() + " asignada al usuario " + usuario.getId());

        } catch (Exception e) {
            System.out.println("Error al asignar la membresía: " + e.getMessage());
        }
    }

    /**
     * Controla el acceso al gimnasio verificando la validez de la membresia del usuario.
     * @param usuario Usuario que intenta ingresar.
     * @return true si la membresia esta activa, false en caso contrario.
     */
    public boolean controlAcceso(Usuario usuario) {
        try {
            if (usuario == null) {
                System.out.println("Usuario incorrecto");
                return false;
            }

            if (usuario.getMembresia() != null && usuario.getMembresia().isActiva()) {
                System.out.println("La membresía está activa para " + usuario.getNombre());
                return true;
            } else {
                System.out.println("La membresía es inactiva o inexistente, por favor vuelve al gym.");
                return false;
            }

        } catch (Exception e) {
            System.out.println("Error al verificar el acceso: " + e.getMessage());
            return false;
        }
    }

    /**
     * Genera un reporte general del gimnasio con informacion de usuarios, clases y membresias.
     * @param gimnasio Objeto Gimnasio del cual se genera el reporte.
     */
    public void generarReporte(Gimnasio gimnasio) {
        try {
            if (gimnasio == null) {
                System.out.println("Error: el gimnasio es nulo.");
                return;
            }

            System.out.println("===== REPORTE GENERAL DEL GIMNASIO =====");
            System.out.println("Fecha de generación: " + LocalDate.now());
            System.out.println();

            System.out.println("USUARIOS ACTIVOS:");
            for (Usuario u : gimnasio.getListUsuarios()) {
                if (u.getMembresia() != null && u.getMembresia().isActiva()) {
                    System.out.println("- " + u.getNombre() + " | ID: " + u.getId() + " | Tipo membresía: " + u.getMembresia().getTipo());
                }
            }

            System.out.println();
            System.out.println("VENCIMIENTO DE MEMBRESÍAS:");
            for (Usuario u : gimnasio.getListUsuarios()) {
                if (u.getMembresia() != null) {
                    System.out.println("- " + u.getNombre() + " | Vence: " + u.getMembresia().getFechaVencimiento());
                }
            }

            System.out.println();
            System.out.println("CLASES MÁS RESERVADAS:");
            if (gimnasio.getListClases().isEmpty()) {
                System.out.println("No hay clases registradas.");
            } else {
                Clase claseMasReservada = null;
                int maxReservas = -1;

                for (Clase c : gimnasio.getListClases()) {
                    int reservas = 0;
                    for (Usuario u : gimnasio.getListUsuarios()) {
                        if (u.getListClases().contains(c)) {
                            reservas++;
                        }
                    }

                    if (reservas > maxReservas) {
                        maxReservas = reservas;
                        claseMasReservada = c;
                    }
                }

                if (claseMasReservada != null) {
                    System.out.println("Clase: " + claseMasReservada.getId() + " | Reservas: " + maxReservas);
                } else {
                    System.out.println("No se encontraron reservas de clases.");
                }
            }

            System.out.println("========================================");

        } catch (Exception e) {
            System.out.println("Error al generar el reporte: " + e.getMessage());
        }
    }

    /**
     * Metodo para aplicar un hash SHA-256 a la contrasena en formato de bytes.
     * @param contrasena Contrasena a hashear.
     * @return Arreglo de bytes con el hash generado.
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
     * Retorna la contrasena (hasheada) del recepcionista.
     *
     * @return contrasena en formato String
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Retorna la lista de clases que administra el recepcionista.
     *
     * @return lista de objetos Clase
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * Asigna la lista de clases que administra el recepcionista.
     *
     * @param listClases lista de objetos Clase a asignar
     */
    public void setListClases(ArrayList<Clase> listClases) {
        this.listClases = listClases;
    }


    /**
     * Asigna la lista de usuarios del recepcionista.
     *
     * @param listUsuarios lista de objetos Usuario a asignar
     */
    public void setListUsuarios(ArrayList<Usuario> listUsuarios) {
        this.listUsuarios = listUsuarios;
    }

    /**
     * Retorna la lista de usuarios registrados por el recepcionista.
     *
     * @return lista de objetos Usuario
     */
    public ArrayList<Clase> getListClases() {
        return listClases;
    }

    /**
     * Retorna el turno de trabajo del recepcionista.
     *
     * @return turno en formato String
     */
    public String getTurno() {return turno;}

    /**
     * Asigna el turno de trabajo del recepcionista.
     *
     * @param turno nuevo turno a asignar
     */
    public void setTurno(String turno) {
        this.turno = turno;
    }

    /**
     * Retorna la lista de membresias que puede gestionar el recepcionista.
     *
     * @return lista de objetos Membresia
     */
    public List<Membresia> getListMembresias() { return listMembresias; }

    /**
     * Asigna la lista de membresias que puede gestionar el recepcionista.
     *
     * @param listMembresias lista de objetos Membresia a asignar
     */
    public void setListMembresias(ArrayList<Membresia> listMembresias) { this.listMembresias = listMembresias; }
}
