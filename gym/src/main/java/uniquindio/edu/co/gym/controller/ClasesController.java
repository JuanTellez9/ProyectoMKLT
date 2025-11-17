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
        Entrenador entre=new Entrenador("Andres","12","12","jkj","kjj","jjk");
        gimnasio.registrarEntrenador(entre);

        // Horas disponibles
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
        ArrayList<Entrenador> entrenadores = gimnasio.getListEntrenadores();
        if (entrenadores == null) entrenadores = new ArrayList<>();

        comboEntrenador.setItems(FXCollections.observableArrayList(entrenadores));
        // Lista de clases
        List<Clase> clases = gimnasio.getListClases();
        if (clases == null) clases = new ArrayList<>();

        listaClases.setAll(clases);
        tableClase.setItems(listaClases);

        // Tabla de usuarios inscritos
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
        try {
            LocalDate fechaCreacion = LocalDate.now();
            Estudiante est=new Estudiante("kebyn","111","23534534","portal","10 nov","",fechaCreacion,"inge","1","inge");

            gimnasio.registrarEstudiantes(est);

            String id = textId.getText();
            ClaseGrupal nombre = comboClaseGrupal.getValue();
            Semana dia = comboDiaSemana.getValue();
            String inicioHora = comboHoraInicio.getValue();
            String finHora = comboHoraFin.getValue();
            int cupo = Integer.parseInt(textCupoMaximo.getText());

            Entrenador entrenadorSeleccionado = comboEntrenador.getValue();

            if (entrenadorSeleccionado == null) {
                mostrarAlerta("Debe seleccionar un entrenador.");
                return;
            }

            Clase nueva = new Clase(
                    id,
                    cupo,
                    inicioHora,
                    finHora,
                    dia,
                    nombre,
                    entrenadorSeleccionado
            );

            gimnasio.registrarClase(nueva);
            entrenadorSeleccionado.agregarClase(nueva);
            listaClases.add(nueva);

            comboClases.getItems().add(nueva);
            comboClasesRegistro.getItems().add(nueva);

            limpiarFormulario();

        } catch (NumberFormatException e) {
            mostrarAlerta("El cupo máximo debe ser un número.");
        }
    }



    @FXML
    public void registrarUsuarioEnClase() {

        // Clase seleccionada
        Clase clase = comboClasesRegistro.getValue();
        String idUsuarioIngresado = usuarioRegisClass.getText();

        if (clase == null || idUsuarioIngresado == null || idUsuarioIngresado.isEmpty()) {
            mostrarAlerta("Debe seleccionar una clase y escribir el ID del usuario.");
            return;
        }

        // Unir los tres arrays en uno solo
        List<Usuario> todosLosUsuarios = new ArrayList<>();
        todosLosUsuarios.addAll(gimnasio.getListTrabajadorUQ());
        todosLosUsuarios.addAll(gimnasio.getListExterno());
        todosLosUsuarios.addAll(gimnasio.getListEstudiante());

        // Buscar usuario por ID
        Usuario usuarioEncontrado = null;
        for (Usuario u : todosLosUsuarios) {
            if (u.getID().equals(idUsuarioIngresado)) {
                usuarioEncontrado = u;
                break;
            }
        }

        if (usuarioEncontrado == null) {
            mostrarAlerta("No existe un usuario con ese ID.");
            return;
        }

        // Obtener inscritos reales
        ArrayList<Usuario> inscritos = obtenerUsuariosInscritosReales(clase);

        // Verificar si ya está inscrito
        if (inscritos.contains(usuarioEncontrado)) {
            mostrarAlerta("El usuario ya está inscrito en esta clase.");
            return;
        }

        // Verificar cupos
        int cuposDisponibles = clase.getCupoMaximo() - inscritos.size();
        if (cuposDisponibles <= 0) {
            mostrarAlerta("No hay cupos disponibles.");
            return;
        }

        // Registrar al usuario completo dentro de la clase
        boolean registrado = clase.inscribirUsuario(usuarioEncontrado);

        if (!registrado) {
            mostrarAlerta("No se pudo registrar. Cupo lleno o error interno.");
            return;
        }

        mostrarAlerta("Usuario registrado correctamente.");

        // Actualizar tabla si está viendo esa clase
        if (comboClases.getValue() == clase) {
            listaUsuarios.setAll(inscritos);
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
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
