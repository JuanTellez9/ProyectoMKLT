package uniquindio.edu.co.gym.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import uniquindio.edu.co.gym.model.*;




public class ClasesController {
    @FXML private TextField textId;
    @FXML private ComboBox<String> comboHoraInicio;
    @FXML private ComboBox<String> comboHoraFin;
    @FXML private TextField textCupoMaximo;
    @FXML private ComboBox<ClaseGrupal> comboClaseGrupal;
    @FXML private ComboBox<Semana> comboDiaSemana;
    @FXML private ComboBox<Clase> comboClases;




    @FXML private TableView<Clase> tableClase;
    @FXML private TableColumn<Clase, String> colId;
    @FXML private TableColumn<Clase, String> colNombre;
    @FXML private TableColumn<Clase, String> colDia;
    @FXML private TableColumn<Clase, String> colInicio;
    @FXML private TableColumn<Clase, String> colFin;
    @FXML private TableColumn<Clase, String> colCupo;
    @FXML private TableView<Usuario> tablaUsuarios;
    @FXML private TableColumn<Usuario, String> colNombreUsuario;
    @FXML private TableColumn<Usuario, String> colTipoUsuario;
    @FXML private TableColumn<Usuario, String> colDetalle;

    // Listas observables
    private final Gimnasio gimnasio = Gimnasio.getInstance();
    private final ObservableList<Clase> listaClases = FXCollections.observableArrayList();
    private final ObservableList<Usuario> listaUsuarios = FXCollections.observableArrayList();





    @FXML
    public void initialize (){
        //Inicializar ComboBox

        comboHoraInicio.getItems().addAll("6:00 a.m", "7:00 a.m", "8:00 a.m", "9:00 a.m", "10:00 a.m", "11:00 a.m", "1:00 p.m","2:00 p.m", "3:00 p.m", "4:00 p.m", "5:00 p.m", "6:00 p.m", "7:00 p.m", "8:00 p.m");
        comboHoraFin.getItems().addAll("6:00 a.m", "7:00 a.m", "8:00 a.m", "9:00 a.m", "10:00 a.m", "11:00 a.m", "1:00 p.m","2:00 p.m", "3:00 p.m", "4:00 p.m", "5:00 p.m", "6:00 p.m", "7:00 p.m", "8:00 p.m");
        //comboClaseGrupal.getItems().addAll("Zumba", "Spinnig", "Yoga", "Tren Superior", "Full Body", "Aeróbicos");
        //comboDiaSemana.getItems().addAll("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado");

        colId.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getId()));
        colNombre.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getClaseGrupal())));
        colInicio.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getHoraInicio()));
        colFin.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getHoraFin()));
        colCupo.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getCupoMaximo())));
        comboDiaSemana.getItems().addAll(Semana.values());
        comboClaseGrupal.getItems().addAll(ClaseGrupal.values());
        colDia.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getSemana())));
        colNombre.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getClaseGrupal())));
        comboHoraInicio.getItems().addAll((comboHoraInicio.getValue()));
        comboHoraFin.getItems().addAll((comboHoraFin.getValue()));

        listaClases.setAll(gimnasio.getListClases());
        tableClase.setItems(listaClases);
        comboClases.getItems().addAll(listaClases);

        // Configurar columnas de tabla de usuarios
        colNombreUsuario.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNombre()));

}
    @FXML
    public void mostrarUsuarios() {
        Clase claseSeleccionada = comboClases.getValue();

        if (claseSeleccionada != null) {
            // Aquí usamos la lista que está en Clase
            listaUsuarios.setAll(claseSeleccionada.getListUsuariosInscritos());
            tablaUsuarios.setItems(listaUsuarios);
        } else {
            mostrarAlerta("Debe seleccionar una clase para ver los usuarios inscritos.");
        }
    }
    @FXML
    public void registrarClase(){
        try {

            String id = textId.getText();
            ClaseGrupal nombre = comboClaseGrupal.getValue();
            Semana dia = comboDiaSemana.getValue();
            String inicioHora = comboHoraInicio.getValue();
            String finHora = comboHoraFin.getValue();
            int cupo =Integer.parseInt(textCupoMaximo.getText());


            Clase nueva =  new Clase(
                    id,
                    cupo,
                    inicioHora,
                    finHora,
                    dia,
                    nombre,
                    new Entrenador("n","12","12","jkj","kjj","jjk")




            );
            Gimnasio.getInstance().registrarClase(nueva);
            listaClases.add(nueva);

            limpiarFormulario();



        } catch (NumberFormatException e){}
    }
    private void limpiarFormulario() {
        textId.clear();
        comboHoraInicio.setValue(null);
        comboHoraFin.setValue(null);
        textCupoMaximo.clear();
        comboClaseGrupal.setValue(null);
        comboDiaSemana.setValue(null);
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Validación");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}