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
        colId.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getId())));

        // Cargar datos existentes desde el singleton
        listaMembresias.setAll(gimnasio.getListMembresia());
        tableMembresia.setItems(listaMembresias);
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
    private void guardarMembresias() {

        // ===== VALIDACIONES =====
        if (campoVacio(textid.getText(), "ID")) return;
        if (campoVacio(textCosto.getText(), "Costo")) return;
        if (comboVacio(DateFechaInicio.getValue(), "Fecha de inicio")) return;
        if (comboVacio(DateFechaVencimiento.getValue(), "Fecha de vencimiento")) return;
        if (comboVacio(comboBeneficio.getValue(), "Beneficio")) return;
        if (comboVacio(comboTipo.getValue(), "Tipo de membresía")) return;
        if (comboVacio(comboNivel.getValue(), "Nivel de membresía")) return;

        // ===== VALIDAR QUE ID y COSTO sean números =====
        int id;
        double costo;

        try {
            id = Integer.parseInt(textid.getText());
        } catch (NumberFormatException e) {
            mostrarAlerta("El campo ID debe ser un número entero.");
            return;
        }

        try {
            costo = Double.parseDouble(textCosto.getText());
        } catch (NumberFormatException e) {
            mostrarAlerta("El campo Costo debe ser un número válido.");
            return;
        }

        // ===== CONVERTIR FECHAS =====
        Date fechaInicio = java.sql.Date.valueOf(DateFechaInicio.getValue());
        Date fechaVencimiento = java.sql.Date.valueOf(DateFechaVencimiento.getValue());

        // ===== VALIDACIÓN DE FECHA =====
        if (fechaVencimiento.before(fechaInicio)) {
            mostrarAlerta("La fecha de vencimiento debe ser mayor o igual a la fecha de inicio.");
            return;
        }

        // ===== EXTRAER DEMÁS CAMPOS =====
        boolean estado = checkActivo.isSelected();
        String beneficio = comboBeneficio.getValue();
        Tipo tipo = comboTipo.getValue();
        Nivel nivel = comboNivel.getValue();

        // ===== CREAR OBJETO =====
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

        // ===== GUARDAR =====
        gimnasio.registrarMembresia(nueva);
        listaMembresias.add(nueva);

        limpiarFormulario();
        mostrarAlerta("Membresía registrada exitosamente.");
    }


    private void limpiarFormulario() {
        textid.clear();
        textCosto.clear();
        DateFechaInicio.setValue(null);
        DateFechaVencimiento.setValue(null);
        checkActivo.setSelected(false);
        comboBeneficio.setValue(null);
        comboNivel.setValue(null);
        comboTipo.setValue(null);
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Validación");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}