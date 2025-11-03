package uniquindio.edu.co.gym.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Recepcionista extends Persona {
    private String turno;
    private String contrasena;
    private ArrayList<Clase> listClases;
    private ArrayList<Membresia> listMembresias;
    private ArrayList<Usuario> listUsuarios;


    public Recepcionista(String nombre, String ID, String telefono, String direccion, String fechaNacimiento, String turno,String contrasena) {
        super(nombre, ID, telefono, direccion, fechaNacimiento);
        this.turno = turno;
        this.contrasena=contrasena;
        this.listClases = new ArrayList<>();
        this.listMembresias = new ArrayList<>();
        this.listUsuarios = new ArrayList<>();
    }

    public boolean verificarUsuario(Usuario usuario){
        boolean bandera = false;
        for(Usuario usu : listUsuarios){
            if(usu.getID() == usuario.getID()){
                bandera = true;
            }
        }
        return bandera;
    }
    public void registrarUsuario(Usuario usuario) {
        if (!verificarUsuario(usuario)) {
            listUsuarios.add(usuario);
            System.out.println("Usuario registrado: " + usuario.getNombre());
        } else {
            System.out.println("El usuario ya existe" + usuario.getNombre());
        }
    }

    public void reservarClase(Gimnasio gimnasio, Usuario usuario, Clase clase) {
        try {
            if (gimnasio == null || usuario == null || clase == null) {
                System.out.println("Error: datos inválidos (usuario, clase o gimnasio nulo).");
                return;
            }

            boolean encontrada = false;
            Clase claseEncontrada = null;

            for (Clase c : gimnasio.getListClases()) {
                if (c.getId() == clase.getId()) {
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
    public void asignarMembresia(Usuario usuario, Membresia membresia) {
        try {
            if (usuario == null || membresia == null) {
                System.out.println("Error: el usuario o la membresía son nulos.");
                return;
            }

            usuario.setMembresia(membresia);
            System.out.println("Membresía " + membresia.getTipo() + " asignada al usuario " + usuario.getID());

        } catch (Exception e) {
            System.out.println("Error al asignar la membresía: " + e.getMessage());
        }
    }

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
                    System.out.println("- " + u.getNombre() + " | ID: " + u.getID() + " | Tipo membresía: " + u.getMembresia().getTipo());
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

    public List<Clase> getListClases() {
        return listClases;
    }

    public void setListClases(ArrayList<Clase> listClases) {
        this.listClases = listClases;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getContrasena() { return contrasena; }

    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public List<Membresia> getListMembresias() { return listMembresias; }

    public void setListMembresias(ArrayList<Membresia> listMembresias) { this.listMembresias = listMembresias; }



    @Override
    public String toString() {
        return "Recepcionista{" +
                "turno='" + turno + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", listClases=" + listClases +
                ", listMembresias=" + listMembresias +
                ", listUsuarios=" + listUsuarios +
                '}';
    }


}
