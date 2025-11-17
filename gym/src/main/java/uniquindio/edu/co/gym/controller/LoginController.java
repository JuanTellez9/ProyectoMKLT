package uniquindio.edu.co.gym.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import uniquindio.edu.co.gym.model.Administrador;
import uniquindio.edu.co.gym.model.Gimnasio;
import uniquindio.edu.co.gym.model.UsuarioLogueado;

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

}
