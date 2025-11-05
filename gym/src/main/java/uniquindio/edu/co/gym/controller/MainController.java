package uniquindio.edu.co.gym.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import uniquindio.edu.co.gym.Navigator;

public class MainController {

    @FXML private StackPane contentArea;

    private void setContent(Node node) {
        contentArea.getChildren().setAll(node);
    }
    @FXML
    public void goClases() {
        setContent(Navigator.loadView("ClasesView.fxml"));
    }

    @FXML
    public void goMembresias() {
        setContent(Navigator.loadView("MembresiasView.fxml"));
    }

    @FXML
    public void goMaquinas() {
        setContent(Navigator.loadView("MaquinasView.fxml"));
    }

    @FXML
    public void goPagos() {
        setContent(Navigator.loadView("PagosView.fxml"));
    }

    @FXML
    public void goPersonas() {
        setContent(Navigator.loadView("PersonasView.fxml"));
    }
}
