package uniquindio.edu.co.gym.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PersonasController {
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtID;



    @FXML
    private void onGuardarPersona(){
        String nombre = txtNombre.getText();

        System.out.println(nombre);
    }

}
