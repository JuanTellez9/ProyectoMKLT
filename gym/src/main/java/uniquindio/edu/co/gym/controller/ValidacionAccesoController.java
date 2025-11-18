package uniquindio.edu.co.gym.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import uniquindio.edu.co.gym.model.*;
import uniquindio.edu.co.gym.util.EmailUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class ValidacionAccesoController {

    @FXML private TextField txtBuscarID;
    @FXML private TextField txtEmail;

    @FXML private VBox boxInfo;

    @FXML private Label lblNombre;
    @FXML private Label lblID;
    @FXML private Label lblTelefono;
    @FXML private Label lblDireccion;
    @FXML private Label lblMembresia;
    @FXML private Label lblEstado;
    @FXML private ProgressIndicator loaderEmail;
    @FXML private Button btnEnviar;

    private final Gimnasio gym = Gimnasio.getInstance();


    // -------------------------
    //  VALIDAR ACCESO
    // -------------------------
    private LocalDate convertir(Date date) {
        return date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
    }

    @FXML
    private void validarAcceso() {

        String id = txtBuscarID.getText().trim();

        if (id.isEmpty()) {
            mostrar("Debe ingresar un ID.");
            return;
        }

        Usuario u = buscarUsuario(id);

        if (u == null) {
            mostrar("No existe un usuario con ese ID.");
            boxInfo.setVisible(false);
            return;
        }

        boxInfo.setVisible(true);

        lblNombre.setText("Nombre: " + u.getNombre());
        lblID.setText("ID: " + u.getID());
        lblTelefono.setText("Teléfono: " + u.getTelefono());
        lblDireccion.setText("Dirección: " + u.getDireccion());

        Membresia mem = u.getMembresia();

        if (mem == null) {
            lblMembresia.setText("Membresía: No registrada");
            lblEstado.setText("ESTADO: SIN ACCESO");
            lblEstado.setStyle("-fx-text-fill: red;");
            return;
        }

        lblMembresia.setText("Membresía: " + mem.getTipo() + " — $" + mem.getCosto());

        // Convertir fechas
        LocalDate hoy = LocalDate.now();
        LocalDate inicio = convertir(mem.getFechaInicio());
        LocalDate fin = convertir(mem.getFechaVencimiento());

        if (hoy.isBefore(inicio) || hoy.isAfter(fin)) {
            lblEstado.setText("ESTADO: MEMBRESÍA VENCIDA X");
            lblEstado.setStyle("-fx-text-fill: red;");
        } else {
            lblEstado.setText("ESTADO: ACCESO PERMITIDO ✔");
            lblEstado.setStyle("-fx-text-fill: green;");
        }
    }

    // -------------------------
    //  BUSCAR USUARIO EN LISTAS
    // -------------------------
    private Usuario buscarUsuario(String id) {

        ArrayList<Usuario> todos = new ArrayList<>();

        todos.addAll(gym.getListEstudiante());
        todos.addAll(gym.getListTrabajadorUQ());
        todos.addAll(gym.getListExterno());

        for (Usuario u : todos) {
            if (u.getID().equals(id)) return u;
        }

        return null;
    }


    // -------------------------
    private void mostrar(String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }


    @FXML
    private void enviarEmail() {

        String correo = txtEmail.getText().trim();

        if (correo.isEmpty()) {
            mostrar("Debe ingresar un correo.");
            return;
        }

        // Activar loader
        loaderEmail.setVisible(true);
        btnEnviar.setDisable(true);

        new Thread(() -> {

            try {

                EmailUtil.enviarCorreo(
                        correo,
                        "Aviso sobre tu Membresía - Gimnasio UQ",
                        "Desde Gimnasio UQ queremos decirte que tu membresía está por vencer o ya venció.\nPor favor vuelve pronto."
                );

                Platform.runLater(() -> mostrar("Correo enviado correctamente "));

            } catch (Exception e) {
                e.printStackTrace();
                Platform.runLater(() -> mostrar("Error al enviar el correo."));
            }

            // Desactivar loader
            Platform.runLater(() -> {
                loaderEmail.setVisible(false);
                btnEnviar.setDisable(false);
            });

        }).start();
    }


}
