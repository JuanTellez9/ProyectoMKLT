package uniquindio.edu.co.gym.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import uniquindio.edu.co.gym.model.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class LoginController {

    @FXML private TextField txtID;
    @FXML private PasswordField txtContrasena;
    @FXML private ComboBox<String> comboTipo;
    @FXML private Label lblError;
    Gimnasio gym=Gimnasio.getInstance();






    @FXML
    public void onLogin() {
        String id = txtID.getText();
        String tipo = comboTipo.getValue();
        String contrasena = txtContrasena.getText();

        if (id == null || id.isBlank()
                || contrasena == null || contrasena.isBlank()
                || tipo == null || tipo.isBlank()) {
            lblError.setText("Completa todos los campos.");
            return;
        }

        UsuarioLogueado auth = UsuarioLogueado.getInstance();
        auth.iniciarSesion(id,tipo,contrasena);

        boolean ok = auth.isSesionActiva();

        if (!ok) {
            lblError.setText("Credenciales incorrectas.");
            return;
        }

        abrirDashboard();
        cerrarLogin();
    }

    private void abrirDashboard() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/uniquindio/edu/co/gym/view/MainLayout.fxml")
            );
            Parent root = loader.load();

            Scene scene = new Scene(root, 1350, 670);
            scene.getStylesheets().add(
                    getClass().getResource("/uniquindio/edu/co/gym/css/styles.css").toExternalForm()
            );

            Stage stage = new Stage();
            stage.setTitle("Gimnasio UQ • Dashboard");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            lblError.setText("Error cargando la aplicación.");
        }
    }

    private void cerrarLogin() {
        Stage stage = (Stage) txtID.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void initialize() {
        crearAdministradorPorDefecto();
        crearMembresiasPorDefecto();
        crearUsuariosPorDefecto();
        crearClasesPorDefecto();
        crearRecepcionistaPorDefecto();
        crearEntrenadorPorDefecto();
        crearMaquinasPorDefecto();
    }


    private void crearAdministradorPorDefecto(){
        Administrador administrador = gym.getAdministrador();

        if (administrador == null) {

            Administrador admin = new Administrador(
                    "Admin Principal",
                    "123",
                    "0000000000",
                    "portal de balcones",
                    "1990-01-01",
                    "Admin. Empresas",
                    ""
            );

            String contrasena = "123";
            byte[] hash = admin.hashearContrasenaBytes(contrasena);
            admin.setContrasena(admin.bytesToHex(hash));

            gym.setAdministrador(admin);
        }
    }

    private void crearRecepcionistaPorDefecto(){
        ArrayList<Recepcionista> recepcionista = gym.getListRecepcionista();

        if (recepcionista.size()==0) {

            Recepcionista recep = new Recepcionista(
                    "Claudia Martinez",
                    "12",
                    "0000000000",
                    "portal de balcones",
                    "1995-01-01",
                    "Dia",
                    "12"
            );

            String contrasena = "12";
            byte[] hash = recep.hashearContrasenaBytes(contrasena);
            recep.setContrasena(recep.bytesToHex(hash));

            gym.registrarRecepcionista(recep);
        }
    }

    private Date convertirFecha(String fecha) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(fecha);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void crearMembresiasPorDefecto() {

        if (gym.getListMembresia().isEmpty()) {

            Membresia basica = new Membresia(
                    123,
                    70000,
                    convertirFecha("01/01/2025"),
                    convertirFecha("01/01/2026"),
                    true,
                    Beneficio.VALORACIONFISICA.toString(),
                    Tipo.MENSUAL,
                    Nivel.BASICO
            );

            Membresia premium = new Membresia(
                    1234,
                    120000,
                    convertirFecha("01/01/2025"),
                    convertirFecha("01/01/2026"),
                    true,
                    Beneficio.PROTEINA.toString(),
                    Tipo.TRIMESTRAL,
                    Nivel.PREMIUM
            );

            Membresia vip = new Membresia(
                    12345,
                    200000,
                    convertirFecha("01/01/2025"),
                    convertirFecha("01/01/2026"),
                    true,
                    Beneficio.ACOMPANANTEALMES.toString(),
                    Tipo.ANUAL,
                    Nivel.VIP
            );

            // 🔥 MEMBRESÍA VENCIDA (hoy ya está vencida)
            Membresia vencida = new Membresia(99999, 50000,convertirFecha("01/01/2023"),convertirFecha("01/01/2024"),true,Beneficio.VALORACIONFISICA.toString(), Tipo.MENSUAL, Nivel.BASICO);

            gym.registrarMembresia(basica);
            gym.registrarMembresia(premium);
            gym.registrarMembresia(vip);
            gym.registrarMembresia(vencida);
        }
    }

    private void registrarPagoAutomatico(Usuario usuario) {

        Membresia m = usuario.getMembresia();
        if (m == null) return;

        Pago p = new Pago(
                usuario.getID(),                         // ID del pago
                "Pago de membresía " + m.getTipo(),      // Detalles
                (int) m.getCosto(),                      // Valor
                new java.util.Date(),                     // Fecha actual
                usuario
        );

        gym.registrarPagos(p);
    }

    private Membresia buscarMembresiaPorId(int id) {
        for (Membresia m : gym.getListMembresia()) {
            if (m.getId() == id) return m;
        }
        return null;
    }



    private void crearUsuariosPorDefecto() {

        if (gym.getListEstudiante().isEmpty()
                && gym.getListTrabajadorUQ().isEmpty()
                && gym.getListExterno().isEmpty()) {

            // ==========================
            // Estudiante BASICO
            // ==========================
            Estudiante est = new Estudiante(
                    "Carlos Básico", "E01", "3000000001", "UQ", "01/01/2000",
                    LocalDate.now(), "Ing Sistemas", "3", "Ingeniería"
            );

            Membresia mBasica = buscarMembresiaPorNivel(Nivel.BASICO);
            est.setMembresia(mBasica);
            mBasica.registrarUsuario(est);
            gym.registrarEstudiante(est);

            // Crear pago
            registrarPagoAutomatico(est);


            // ==========================
            // Trabajador PREMIUM
            // ==========================
            TrabajadorUQ trab = new TrabajadorUQ(
                    "Laura Premium", "T01", "3000000002", "UQ", "02/02/1998",
                    LocalDate.now(), "Profesora", "Ingeniería"
            );

            Membresia mPremium = buscarMembresiaPorNivel(Nivel.PREMIUM);
            trab.setMembresia(mPremium);
            mPremium.registrarUsuario(trab);
            gym.registrarTrabajadorUQ(trab);

            // Crear pago
            registrarPagoAutomatico(trab);


            // ==========================
            // Externo VIP
            // ==========================
            Externo ex = new Externo(
                    "Pedro VIP", "X01", "3000000003", "Calle 123", "03/03/1995",
                    LocalDate.now(), "Programador"
            );


            Membresia mVip = buscarMembresiaPorNivel(Nivel.VIP);
            ex.setMembresia(mVip);
            mVip.registrarUsuario(ex);
            gym.registrarExterno(ex);

            // Crear pago
            registrarPagoAutomatico(ex);

            // ==========================
// Usuario con membresía VENCIDA
// ==========================

            Externo exVencido = new Externo(
                    "Usuario Vencido", "X99", "3121234567", "Calle Sin Acceso",
                    "05/05/1990", LocalDate.now(), "Desempleado"
            );

            Membresia memVencida = buscarMembresiaPorId(99999);  // ID de la vencida

            exVencido.setMembresia(memVencida);
            memVencida.registrarUsuario(exVencido);

            gym.registrarExterno(exVencido);

            // Registrar pago (será viejo)
            registrarPagoAutomatico(exVencido);

        }
    }


    private Membresia buscarMembresiaPorNivel(Nivel nivel) {
        for (Membresia m : gym.getListMembresia()) {
            if (m.getNivel() == nivel) return m;
        }
        return null;
    }


    private void crearClasesPorDefecto() {

        if (gym.getListClases().isEmpty()) {

            Entrenador entrenador = gym.getListEntrenadores().isEmpty()
                    ? new Entrenador("Andrés", "999", "3110002200", "UQ", "01/01/1990", "Mañana")
                    : gym.getListEntrenadores().get(0);

            Clase c1 = new Clase("C01", 10, "6:00 a.m", "7:00 a.m",
                    Semana.LUNES, ClaseGrupal.ZUMBA, entrenador);

            Clase c2 = new Clase("C02", 15, "7:00 a.m", "8:00 a.m",
                    Semana.MIERCOLES, ClaseGrupal.SPINNING, entrenador);

            gym.registrarClase(c1);
            gym.registrarClase(c2);
        }
    }

    private void crearEntrenadorPorDefecto() {

        if (gym.getListEntrenadores().isEmpty()) {

            Entrenador entrenador = new Entrenador(
                    "Andrés",
                    "999",
                    "3110002200",
                    "Universidad del Quindío",
                    "01/01/1990",
                    "Mañana"
            );

            gym.registrarEntrenador(entrenador);
        }
    }

    private void crearMaquinasPorDefecto() {

        if (gym.getListMaquina().isEmpty()) {

            Maquina m1 = new Maquina(
                    "Caminadora",
                    "M001",
                    "TM-300X",
                    "Cardio / correr"
            );

            Maquina m2 = new Maquina(
                    "Bicicleta Estática",
                    "M002",
                    "BK-120",
                    "Cardio / resistencia"
            );

            Maquina m3 = new Maquina(
                    "Máquina de Pecho",
                    "M003",
                    "CH-550",
                    "Pecho / fuerza"
            );

            Maquina m4 = new Maquina(
                    "Multifuerza",
                    "M004",
                    "MF-900",
                    "Cuerpo completo / fuerza"
            );

            Maquina m5 = new Maquina(
                    "Máquina de Piernas",
                    "M005",
                    "LG-450",
                    "Piernas / cuadriceps"
            );

            Maquina m6 = new Maquina(
                    "Máquina de Espalda",
                    "M006",
                    "BK-410",
                    "Espalda / dorsales"
            );

            Maquina m7 = new Maquina(
                    "Máquina de Abdomen",
                    "M007",
                    "AB-220",
                    "Core / abdomen"
            );

            gym.registrarMaquina(m1);
            gym.registrarMaquina(m2);
            gym.registrarMaquina(m3);
            gym.registrarMaquina(m4);
            gym.registrarMaquina(m5);
            gym.registrarMaquina(m6);
            gym.registrarMaquina(m7);
        }
    }



}

