package uniquindio.edu.co.gym.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import uniquindio.edu.co.gym.model.Gimnasio;
import uniquindio.edu.co.gym.model.Membresia;
import uniquindio.edu.co.gym.model.Nivel;
import uniquindio.edu.co.gym.model.Tipo;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class MembresiasController {

    @FXML private ComboBox<Tipo> comboTipo;
    @FXML private ComboBox<Nivel> comboNivel;
    @FXML private TextField textid;
    @FXML private TextField textCosto;
    @FXML private DatePicker DateFechaInicio;
    @FXML private DatePicker DateFechaVencimiento;
    @FXML private CheckBox checkActivo;
    @FXML private ComboBox<String> comboBeneficio;

    @FXML private TableColumn<Membresia, String> colId;
    @FXML private TableColumn<Membresia, String> colCosto;
    @FXML private TableColumn<Membresia, String> colInicio;
    @FXML private TableColumn<Membresia, String> colVence;
    @FXML private TableColumn<Membresia, String> colEstado;
    @FXML private TableColumn<Membresia, String> colBeneficio;
    @FXML private TableView<Membresia> tableMembresia;
    @FXML private TableColumn<Membresia, String> colTipo;
    @FXML private TableColumn<Membresia, String> colNivel;

    private final Gimnasio gimnasio = Gimnasio.getInstance();
    private final ObservableList<Membresia> listaMembresias = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Inicializar ComboBox
        comboBeneficio.getItems().addAll("Acompañante", "Proteína", "Valoración");

        // Formateador de fechas
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

        // Configurar columnas con conversión de tipos
        comboTipo.getItems().addAll(Tipo.values());
        comboNivel.getItems().addAll(Nivel.values());
        colCosto.setCellValueFactory(data -> new SimpleStringProperty(String.format("%.2f", data.getValue().getCosto())));
        colInicio.setCellValueFactory(data -> new SimpleStringProperty(formatoFecha.format(data.getValue().getFechaInicio())));
        colVence.setCellValueFactory(data -> new SimpleStringProperty(formatoFecha.format(data.getValue().getFechaVencimiento())));
        colEstado.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().isEstado() ? "Activa" : "Inactiva"));
        colBeneficio.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBeneficio()));
        colNivel.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getNivel())));
        colTipo.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getTipo())));

        // Cargar datos existentes desde el singleton
        listaMembresias.setAll(gimnasio.getListMembresia());
        tableMembresia.setItems(listaMembresias);
    }



    @FXML
    private void guardarMembresias() {
        try {
            int id = Integer.parseInt(textid.getText());
            double costo = Double.parseDouble(textCosto.getText());
            Date fechaInicio = java.sql.Date.valueOf(DateFechaInicio.getValue());
            Date fechaVencimiento = java.sql.Date.valueOf(DateFechaVencimiento.getValue());
            boolean estado = checkActivo.isSelected();
            String beneficio = comboBeneficio.getValue();

            // Si tienes ComboBox para tipo y nivel, deberías obtenerlos así:
            // Tipo tipo = comboTipo.getValue();
            // Nivel nivel = comboNivel.getValue();

            // Si no los tienes aún, puedes usar valores por defecto:
            Tipo tipo = comboTipo.getValue(); // ejemplo
            Nivel nivel = comboNivel.getValue(); // ejemplo

            Membresia nueva = new Membresia(
                    id,
                    costo,
                    fechaInicio,
                    fechaVencimiento,
                    estado,
                    beneficio,
                    tipo,
                    nivel
            );

            Gimnasio.getInstance().registrarMembresia(nueva);
            listaMembresias.add(nueva);

            limpiarFormulario();

        } catch (NumberFormatException e) {
            mostrarAlerta("ID y Costo deben ser números válidos.");
        } catch (Exception e) {
            mostrarAlerta("Error al crear la membresía: " + e.getMessage());
        }
    }

    private void limpiarFormulario() {
        textid.clear();
        textCosto.clear();
        DateFechaInicio.setValue(null);
        DateFechaVencimiento.setValue(null);
        checkActivo.setSelected(false);
        comboBeneficio.setValue(null);
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Validación");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}