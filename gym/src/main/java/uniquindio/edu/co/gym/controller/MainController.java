package uniquindio.edu.co.gym.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import uniquindio.edu.co.gym.Navigator;
import uniquindio.edu.co.gym.model.UsuarioLogueado;

public class MainController {

    @FXML private StackPane contentArea;
//t

    @FXML
    public void initialize() {
        if (!UsuarioLogueado.getInstance().isSesionActiva()) {
            System.out.println("Intento de acceso sin sesión. Cerrando app...");
            System.exit(0);
        }

        // Cargar pantalla de bienvenida al iniciar la app
        setContent(Navigator.loadView("WelcomeView.fxml"));
    }

    private void setContent(Node node) {
        contentArea.getChildren().setAll(node);
    }
    @FXML
    public void goClases() {
        setContent(Navigator.loadView("ClasesView.fxml"));
    }

    @FXML
    public void goPerfil() {
        setContent(Navigator.loadView("PerfilView.fxml"));
    }


    @FXML
    public void goMembresias() {
        setContent(Navigator.loadView("MembresiasView.fxml"));
    }

    @FXML
    public void goMaquinas() {
        try {
            Node view = Navigator.loadView("MaquinasView.fxml");
            setContent(view);
        } catch (RuntimeException e) {
            e.printStackTrace(); // Ver el error exacto en consola
        }
    }

    @FXML
    public void goPagos() {
        setContent(Navigator.loadView("PagosView.fxml"));
    }

    @FXML
    public void goPersonas() {
        setContent(Navigator.loadView("PersonasView.fxml"));
    }

    @FXML
    public void logout() {
        try {
            // 1) Cerrar sesión
            UsuarioLogueado.getInstance().cerrarSesion();
            System.out.println("Sesión cerrada");

            // 2) Cerrar ventana actual
            javafx.stage.Stage thisStage =
                    (javafx.stage.Stage) contentArea.getScene().getWindow();
            thisStage.close();

            // 3) Abrir Login otra vez
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(
                    getClass().getResource("/uniquindio/edu/co/gym/view/LoginView.fxml")
            );
            javafx.scene.Parent root = loader.load();

            javafx.scene.Scene scene = new javafx.scene.Scene(root);
            javafx.stage.Stage stage = new javafx.stage.Stage();
            stage.setTitle("Iniciar sesión - Gimnasio UQ");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al cerrar sesión.");
        }
    }


}
