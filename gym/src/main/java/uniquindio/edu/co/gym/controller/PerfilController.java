package uniquindio.edu.co.gym.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import uniquindio.edu.co.gym.model.Persona;
import uniquindio.edu.co.gym.model.UsuarioLogueado;

public class PerfilController {

    @FXML private ImageView imgPerfil;
    @FXML private Label lblNombre;
    @FXML private Label lblID;
    @FXML private Label lblTelefono;
    @FXML private Label lblDireccion;
    @FXML private Label lblNacimiento;

    private static final String URL_IMAGEN_POR_DEFECTO =
            "https://www.shutterstock.com/image-vector/default-avatar-profile-icon-vector-600nw-1725655669.jpg";

    @FXML
    public void initialize() {

        Persona u = UsuarioLogueado.getInstance().getUsuario();

        if (u == null) {
            System.out.println("No hay usuario logueado");
            return;
        }

        // 👉 Elegir la imagen
        String urlFoto = (u.getFoto() == null || u.getFoto().isBlank())
                ? URL_IMAGEN_POR_DEFECTO   // sin foto → usar la default
                : u.getFoto();             // con foto → usar la foto del usuario

        try {
            Image foto = new Image(urlFoto, true);
            imgPerfil.setImage(foto);
        } catch (Exception e) {
            System.out.println("Error cargando imagen, usando default.");
            imgPerfil.setImage(new Image(URL_IMAGEN_POR_DEFECTO));
        }

        // DATOS DEL USUARIO
        lblNombre.setText("Nombre: " + u.getNombre());
        lblID.setText("ID: " + u.getID());
        lblTelefono.setText("Teléfono: " + u.getTelefono());
        lblDireccion.setText("Dirección: " + u.getDireccion());
        lblNacimiento.setText("Fecha de nacimiento: " + u.getFechaNacimiento());
    }
}
