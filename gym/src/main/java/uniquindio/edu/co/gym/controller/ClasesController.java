package uniquindio.edu.co.gym.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import uniquindio.edu.co.gym.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClasesController {

    @FXML private TextField textId;
    @FXML private ComboBox<String> comboHoraInicio;
    @FXML private ComboBox<String> comboHoraFin;
    @FXML private TextField textCupoMaximo;
    @FXML private ComboBox<ClaseGrupal> comboClaseGrupal;
    @FXML private ComboBox<Semana> comboDiaSemana;
    @FXML private ComboBox<Entrenador> comboEntrenador;

    @FXML private ComboBox<Clase> comboClases;
    @FXML private ComboBox<Clase> comboClasesRegistro;
    @FXML private TextField usuarioRegisClass;

    @FXML private TableView<Clase> tableClase;
    @FXML private TableColumn<Clase, String> colId;
    @FXML private TableColumn<Clase, String> colNombre;
    @FXML private TableColumn<Clase, String> colDia;
    @FXML private TableColumn<Clase, String> colInicio;
    @FXML private TableColumn<Clase, String> colFin;
    @FXML private TableColumn<Clase, String> colCupo;
    @FXML private TableColumn<Clase, String> colEntrenador;
    @FXML private TableView<Usuario> tablaUsuarios;
    @FXML private TableColumn<Usuario, String> colNombreUsuario;

    private final Gimnasio gimnasio = Gimnasio.getInstance();
    private final ObservableList<Clase> listaClases = FXCollections.observableArrayList();
    private final ObservableList<Usuario> listaUsuarios = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        // Horas
        ObservableList<String> horas = FXCollections.observableArrayList(
                "6:00 a.m", "7:00 a.m", "8:00 a.m", "9:00 a.m",
                "10:00 a.m", "11:00 a.m", "1:00 p.m", "2:00 p.m",
                "3:00 p.m", "4:00 p.m", "5:00 p.m", "6:00 p.m",
                "7:00 p.m", "8:00 p.m"
        );

        comboHoraInicio.setItems(horas);
        comboHoraFin.setItems(FXCollections.observableArrayList(horas));

        // Enums
        comboClaseGrupal.setItems(FXCollections.observableArrayList(ClaseGrupal.values()));
        comboDiaSemana.setItems(FXCollections.observableArrayList(Semana.values()));

        // Columnas tabla clases
        colId.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getId()));
        colNombre.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getClaseGrupal().toString()));
        colInicio.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getHoraInicio()));
        colFin.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getHoraFin()));
        colCupo.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getCupoMaximo())));
        colDia.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getSemana().toString()));
        colEntrenador.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEntrenador().getNombre()));

        // Entrenadores
        comboEntrenador.setItems(FXCollections.observableArrayList(gimnasio.getListEntrenadores()));

        // Lista de clases
        listaClases.setAll(gimnasio.getListClases());
        tableClase.setItems(listaClases);

        // Los combos comparten la misma lista observable
        comboClases.setItems(listaClases);
        comboClasesRegistro.setItems(listaClases);

        // Tabla de usuarios
        colNombreUsuario.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNombre()));
    }



    // Obtener la lista REAL donde inscribirUsuario() guarda
    private ArrayList<Usuario> obtenerUsuariosInscritosReales(Clase clase) {
        return clase.getListUsario();
    }


    @FXML
    public void mostrarUsuarios() {
        Clase claseSeleccionada = comboClases.getValue();

        if (claseSeleccionada == null) {
            mostrarAlerta("Debe seleccionar una clase para ver los usuarios.");
            return;
        }

        listaUsuarios.setAll(obtenerUsuariosInscritosReales(claseSeleccionada));
        tablaUsuarios.setItems(listaUsuarios);
    }
    @FXML
    public void registrarClase() {

        // === VALIDACIONES ===
        if (campoVacio(textId.getText(), "ID de la clase")) return;
        if (comboVacio(comboClaseGrupal.getValue(), "Clase grupal")) return;
        if (comboVacio(comboDiaSemana.getValue(), "Día de la semana")) return;
        if (comboVacio(comboHoraInicio.getValue(), "Hora de inicio")) return;
        if (comboVacio(comboHoraFin.getValue(), "Hora de fin")) return;
        if (campoVacio(textCupoMaximo.getText(), "Cupo máximo")) return;
        if (comboVacio(comboEntrenador.getValue(), "Entrenador")) return;

        // Validar número
        int cupo;
        try {
            cupo = Integer.parseInt(textCupoMaximo.getText());
            if (cupo <= 0) {
                mostrarAlerta("El cupo máximo debe ser mayor que 0.");
                return;
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("El campo Cupo Máximo debe ser un número entero.");
            return;
        }

        // Extraer valores
        String id = textId.getText();
        ClaseGrupal nombre = comboClaseGrupal.getValue();
        Semana dia = comboDiaSemana.getValue();
        String inicioHora = comboHoraInicio.getValue();
        String finHora = comboHoraFin.getValue();
        Entrenador entrenadorSeleccionado = comboEntrenador.getValue();

        // Crear clase
        Clase nueva = new Clase(
                id, cupo, inicioHora, finHora, dia, nombre, entrenadorSeleccionado
        );

        gimnasio.registrarClase(nueva);
        entrenadorSeleccionado.agregarClase(nueva);

        listaClases.add(nueva);
        limpiarFormulario();

        mostrarAlerta("Clase registrada correctamente.");
    }


    private void mostrar(String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }
    private boolean campoVacio(String valor, String nombreCampo) {
        if (valor == null || valor.trim().isEmpty()) {
            mostrarAlerta("Falta llenar el campo: " + nombreCampo);
            return true;
        }
        return false;
    }

    private boolean comboVacio(Object valor, String nombreCampo) {
        if (valor == null) {
            mostrarAlerta("Debe seleccionar: " + nombreCampo);
            return true;
        }
        return false;
    }


    @FXML
    public void registrarUsuarioEnClase() {

        if (!gimnasio.isRecep()) {
            mostrarAlerta("Debes ser Recepcionista para poder ejecutar esta acción.");
            return;
        }
        if (comboVacio(comboClasesRegistro.getValue(), "Clase para registrar usuario")) return;
        if (campoVacio(usuarioRegisClass.getText(), "ID del usuario")) return;

        Clase clase = comboClasesRegistro.getValue();
        String idUsuario = usuarioRegisClass.getText().trim();

        Recepcionista recep = gimnasio.obtenerRecepcionistaActual();

        if (recep == null) {
            mostrarAlerta("ERROR: No se pudo obtener el recepcionista actual.");
            return;
        }

        String resultado = recep.registrarUsuarioEnClase(idUsuario, clase);

        if (!resultado.equals("OK")) {
            mostrarAlerta(resultado);
            return;
        }

        mostrarAlerta("Usuario registrado correctamente.");
        limpiarFormulario();

        // refrescar tabla si es la misma clase
        if (comboClases.getValue() == clase) {
            listaUsuarios.setAll(clase.getListUsario());
        }
    }



    private void limpiarFormulario() {
        textId.clear();
        textCupoMaximo.clear();
        comboHoraFin.setValue(null);
        comboHoraInicio.setValue(null);
        comboClaseGrupal.setValue(null);
        comboDiaSemana.setValue(null);
        comboEntrenador.setValue(null);
        comboClasesRegistro.setValue(null);
        usuarioRegisClass.clear();
        comboClases.setValue(null);
    }


    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
