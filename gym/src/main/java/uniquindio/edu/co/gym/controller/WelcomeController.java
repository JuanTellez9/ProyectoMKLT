package uniquindio.edu.co.gym.controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WelcomeController {

    @FXML
    private ImageView logoImage;

    public void initialize() {
        logoImage.setImage(new Image(
                "https://www.uniquindio.edu.co/info/uniquindio/media/bloque2.png"
        ));
    }
}
