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



    private boolean campoVacio(String valor, String nombreCampo) {
        if (valor == null || valor.trim().isEmpty()) {
            mostrar("Falta llenar el campo: " + nombreCampo);
            return true;
        }
        return false;
    }

    private boolean correoInvalido(String correo) {
        return !correo.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }


    @FXML
    private void validarAcceso() {

        String id = txtBuscarID.getText().trim();

        // ===== VALIDACIONES =====
        if (campoVacio(id, "ID del usuario")) {
            boxInfo.setVisible(false);
            return;
        }

        Recepcionista recep = gym.obtenerRecepcionistaActual();

        if (recep == null) {
            mostrar("No hay recepcionista logueado.");
            return;
        }

        // ===== USAR LÓGICA DE RECEPCIONISTA =====
        Recepcionista.ResultadoAcceso r = recep.validarAcceso(id);

        if (r.usuario == null) {
            boxInfo.setVisible(false);
            mostrar(r.mensaje);
            return;
        }

        Usuario u = r.usuario;

        boxInfo.setVisible(true);

        lblNombre.setText("Nombre: " + u.getNombre());
        lblID.setText("ID: " + u.getID());
        lblTelefono.setText("Teléfono: " + u.getTelefono());
        lblDireccion.setText("Dirección: " + u.getDireccion());

        Membresia mem = u.getMembresia();

        lblMembresia.setText("Membresía: " + mem.getTipo() + " — $" + mem.getCosto());
        lblEstado.setText("ESTADO: " + r.mensaje);

        lblEstado.setStyle(r.permitido ? "-fx-text-fill: green;" : "-fx-text-fill: red;");
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

        // ===== VALIDACIONES =====
        if (campoVacio(correo, "Correo electrónico")) return;

        if (correoInvalido(correo)) {
            mostrar("El correo ingresado no es válido.");
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

                Platform.runLater(() -> mostrar("Correo enviado correctamente."));

            } catch (Exception e) {
                e.printStackTrace();
                Platform.runLater(() -> mostrar("Error al enviar el correo."));
            }

            Platform.runLater(() -> {
                loaderEmail.setVisible(false);
                btnEnviar.setDisable(false);
            });

        }).start();
    }



}
