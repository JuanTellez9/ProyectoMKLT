package uniquindio.edu.co.gym;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;
import java.net.URL;

public class Navigator {
    public static Node loadView(String fxmlName) {
        try {
            URL url = Navigator.class.getResource("/uniquindio/edu/co/gym/view/" + fxmlName);
            return FXMLLoader.load(url);
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar vista: " + fxmlName, e);
        }
    }
}
