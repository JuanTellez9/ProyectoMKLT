package uniquindio.edu.co.gym;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        // 🔹 PRIMERO: cargar la ventana de LOGIN
        Parent root = FXMLLoader.load(
                getClass().getResource("/uniquindio/edu/co/gym/view/LoginView.fxml")
        );

        Scene scene = new Scene(root, 430, 320);

        // 🔹 Opcional: si quieres usar el mismo CSS también en el login:
        scene.getStylesheets().add(
                getClass().getResource("/uniquindio/edu/co/gym/css/styles.css").toExternalForm()
        );

        stage.setTitle("Iniciar Sesión • Gimnasio UQ");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
