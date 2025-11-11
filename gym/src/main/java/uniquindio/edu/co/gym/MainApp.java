package uniquindio.edu.co.gym;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println(getClass().getResource("/uniquindio/edu/co/gym/view/MainLayout.fxml"));
        Parent root = FXMLLoader.load(
                getClass().getResource("/uniquindio/edu/co/gym/view/MainLayout.fxml")
        );
        Scene scene = new Scene(root, 1250, 650);
        scene.getStylesheets().add(
                getClass().getResource("/uniquindio/edu/co/gym/css/styles.css").toExternalForm()
        );
        stage.setTitle("Gimnasio UQ • Dashboard");
        stage.setScene(scene);
        stage.show();
//        try {
//            Parent root = FXMLLoader.load(getClass().getResource("/uniquindio/edu/co/gymplantilla/view/MainLayout.fxml"));
//            Scene scene = new Scene(root, 1150, 720);
//            stage.setScene(scene);
//            stage.show();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public static void main(String[] args) {
        launch();
    }
}
