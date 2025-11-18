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
    private Gimnasio gym= Gimnasio.getInstance();


    public Recepcionista(String nombre, String ID, String telefono, String direccion, String fechaNacimiento, String turno,String contrasena) {
        super(nombre, ID, telefono, direccion, fechaNacimiento);
        this.turno = turno;
        this.contrasena= Arrays.toString(hashearContrasenaBytes(contrasena));
    }

    public boolean verificarUsuario(Usuario usuario){
        boolean bandera = false;
        for(Usuario usu : gym.getTodosLosUsuarios()){
            if(usu.getID().equals(usuario.getID())){
                bandera = true;
                break;
            }
        }
        return bandera;
    }
    private void registrarPagoAutomatico(Usuario usuario) {

        Membresia m = usuario.getMembresia();
        if (m == null) return;

        Pago p = new Pago(usuario.getID(),"Pago de membresía " + m.getTipo(),(int)m.getCosto(),new java.util.Date(),usuario);


        gym.registrarPagos(p);
    }
    public void registrarEstudiante(Estudiante est, Membresia membresia) {
        if (est == null || membresia == null) return;

        est.setMembresia(membresia);
        membresia.registrarUsuario(est);
        gym.registrarEstudiante(est);

        registrarPagoAutomatico(est);
    }

    public void registrarTrabajadorUQ(TrabajadorUQ trab, Membresia membresia) {
        if (trab == null || membresia == null) return;

        trab.setMembresia(membresia);
        membresia.registrarUsuario(trab);
        gym.registrarTrabajadorUQ(trab);

        registrarPagoAutomatico(trab);
    }
    public class ResultadoAcceso {
        public String mensaje;
        public Usuario usuario;
        public boolean permitido;

        public ResultadoAcceso(String mensaje, Usuario usuario, boolean permitido) {
            this.mensaje = mensaje;
            this.usuario = usuario;
            this.permitido = permitido;
        }
    }

    public ResultadoAcceso validarAcceso(String idUsuario) {

        if (idUsuario == null || idUsuario.isEmpty()) {
            return new ResultadoAcceso("Debe ingresar un ID.", null, false);
        }

        // Obtener todos los usuarios
        ArrayList<Usuario> todos = new ArrayList<>();
        todos.addAll(gym.getListEstudiante());
        todos.addAll(gym.getListTrabajadorUQ());
        todos.addAll(gym.getListExterno());

        Usuario u = null;

        for (Usuario usu : todos) {
            if (usu.getID().equals(idUsuario)) {
                u = usu;
                break;
            }
        }

        if (u == null) {
            return new ResultadoAcceso("No existe un usuario con ese ID.", null, false);
        }

        Membresia mem = u.getMembresia();

        if (mem == null) {
            return new ResultadoAcceso("Usuario sin membresía registrada. SIN ACCESO.", u, false);
        }

        LocalDate hoy = LocalDate.now();
        LocalDate inicio = mem.getFechaInicio().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        LocalDate fin = mem.getFechaVencimiento().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();

        if (hoy.isBefore(inicio) || hoy.isAfter(fin)) {
            return new ResultadoAcceso("MEMBRESÍA VENCIDA ❌", u, false);
        }

        return new ResultadoAcceso("ACCESO PERMITIDO ✔", u, true);
    }



    public void registrarExterno(Externo ext, Membresia membresia) {
        if (ext == null || membresia == null) return;

        ext.setMembresia(membresia);
        membresia.registrarUsuario(ext);
        gym.registrarExterno(ext);

        registrarPagoAutomatico(ext);
    }
    public String registrarUsuarioEnClase(String idUsuario, Clase clase) {

        if (idUsuario == null || idUsuario.isEmpty() || clase == null) {
            return "Debe ingresar un ID y seleccionar una clase.";
        }

        // 1. Obtener todos los usuarios
        List<Usuario> todos = gym.getTodosLosUsuarios();

        // 2. Buscar usuario por ID
        Usuario usuario = null;
        for (Usuario u : todos) {
            if (u.getID().equals(idUsuario)) {
                usuario = u;
                break;
            }
        }

        if (usuario == null) {
            return "No existe un usuario con ese ID.";
        }

        // 3. Validar membresía
        Membresia mem = usuario.getMembresia();
        if (mem == null) {
            return "El usuario no tiene membresía asignada.";
        }


        LocalDate hoy = LocalDate.now();
        LocalDate ini = mem.getFechaInicio().toInstant()
                .atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        LocalDate fin = mem.getFechaVencimiento().toInstant()
                .atZone(java.time.ZoneId.systemDefault()).toLocalDate();

        if (hoy.isBefore(ini) || hoy.isAfter(fin)) {
            return "La membresía del usuario está vencida. No puede acceder a clases.";
        }

        // ✔ Validación por nivel
        if (mem.getNivel() == Nivel.BASICO) {
            return "El plan Básico no permite acceso a clases grupales.";
        }

        // 4. Validar cupos reales (listUsario)
        ArrayList<Usuario> inscritos = clase.getListUsario();
        int cupoReal = clase.getCupoMaximo() - inscritos.size();

        if (cupoReal <= 0) {
            return "No hay cupos disponibles.";
        }

        // 5. Verificar si ya está inscrito
        if (inscritos.contains(usuario)) {
            return "El usuario ya está inscrito en esta clase.";
        }

        // 6. Registrar
        clase.inscribirUsuario(usuario);

        return "OK";
    }




    public ArrayList<Usuario> obtenerUsuariosActivos() {
        ArrayList<Usuario> activos = new ArrayList<>();
        LocalDate hoy = LocalDate.now();

        for (Usuario u : gym.getTodosLosUsuarios()) {

            if (u.getMembresia() == null) continue;

            LocalDate ini = u.getMembresia().getFechaInicio()
                    .toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();

            LocalDate fin = u.getMembresia().getFechaVencimiento()
                    .toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();

            if (!hoy.isBefore(ini) && !hoy.isAfter(fin)) {
                activos.add(u);
            }
        }

        return activos;
    }

    public ArrayList<Usuario> obtenerUsuariosVencidos() {
        ArrayList<Usuario> vencidos = new ArrayList<>();
        LocalDate hoy = LocalDate.now();

        for (Usuario u : gym.getTodosLosUsuarios()) {

            if (u.getMembresia() == null) continue;

            LocalDate fin = u.getMembresia().getFechaVencimiento()
                    .toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();

            if (hoy.isAfter(fin)) {
                vencidos.add(u);
            }
        }

        return vencidos;
    }




    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }





  

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }


  


}
