package uniquindio.edu.co.gym.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import uniquindio.edu.co.gym.Navigator;

public class PerfilController {
    @FXML
    private StackPane contentArea;
    private void setContent(Node node) {
        contentArea.getChildren().setAll(node);
    }

    public void goClases() {
        setContent(Navigator.loadView("Perfil.fxml"));
    }
}
