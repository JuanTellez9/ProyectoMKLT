package uniquindio.edu.co.gym.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import uniquindio.edu.co.gym.model.*;

import java.time.LocalDate;

public class ReportesAdminController {

    private final Gimnasio gym = Gimnasio.getInstance();
    private final Administrador admin = gym.getAdministrador();

    /* TABLA ASISTENCIA */
    @FXML private TableView<Administrador.ReporteAsistencia> tablaAsistencia;
    @FXML private TableColumn<Administrador.ReporteAsistencia, String> colClaseA;
    @FXML private TableColumn<Administrador.ReporteAsistencia, String> colCupoA;
    @FXML private TableColumn<Administrador.ReporteAsistencia, String> colAsistentesA;
    @FXML private TableColumn<Administrador.ReporteAsistencia, String> colOcupacionA;

    /* TABLA INGRESOS */
    @FXML private TableView<Administrador.ReporteIngresos> tablaIngresos;
    @FXML private TableColumn<Administrador.ReporteIngresos, String> colCantidadI;
    @FXML private TableColumn<Administrador.ReporteIngresos, String> colTotalI;

    /* TABLA POPULARES */
    @FXML private TableView<Clase> tablaPopulares;
    @FXML private TableColumn<Clase, String> colClaseP;
    @FXML private TableColumn<Clase, String> colAsistentesP;
    @FXML private TableColumn<Clase, String> colCupoP;



    /* TABLA ACTIVOS */
    @FXML private TableView<Usuario> tablaActivos;
    @FXML private TableColumn<Usuario, String> colNombreA;
    @FXML private TableColumn<Usuario, String> colIdA;
    @FXML private TableColumn<Usuario, String> colTelA;
    @FXML private TableColumn<Usuario, String> colMemA;
    @FXML private TableColumn<Usuario, String> colFinA;

    /* TABLA VENCIDOS */
    @FXML private TableView<Usuario> tablaVencidos;
    @FXML private TableColumn<Usuario, String> colNombreV;
    @FXML private TableColumn<Usuario, String> colIdV;
    @FXML private TableColumn<Usuario, String> colTelV;
    @FXML private TableColumn<Usuario, String> colMemV;
    @FXML private TableColumn<Usuario, String> colFinV;


    @FXML
    public void initialize() {

        /* ---- Tabla Asistencia ---- */
        colClaseA.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getClase()));
        colCupoA.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getCupo()));
        colAsistentesA.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getAsistentes()));
        colOcupacionA.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getOcupacion()));

        /* ---- Tabla Ingresos ---- */
        colCantidadI.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getCantidad()));
        colTotalI.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getTotal()));

        /* ---- Tabla Populares ---- */
        colClaseP.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getClaseGrupal().toString()));
        colAsistentesP.setCellValueFactory(d ->
                new SimpleStringProperty(String.valueOf(
                        d.getValue().getListUsario() != null ? d.getValue().getListUsario().size() : 0
                ))
        );
        /* ---- Tabla Activos ---- */
        colNombreA.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getNombre()));
        colIdA.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getID()));
        colTelA.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getTelefono()));
        colMemA.setCellValueFactory(d -> new SimpleStringProperty(
                d.getValue().getMembresia() != null ? d.getValue().getMembresia().getTipo().toString() : "Sin membresía"
        ));
        colFinA.setCellValueFactory(d -> new SimpleStringProperty(
                d.getValue().getMembresia() != null
                        ? d.getValue().getMembresia().getFechaVencimiento().toString()
                        : "---"
        ));

        /* ---- Tabla Vencidos ---- */
        colNombreV.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getNombre()));
        colIdV.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getID()));
        colTelV.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getTelefono()));
        colMemV.setCellValueFactory(d -> new SimpleStringProperty(
                d.getValue().getMembresia() != null ? d.getValue().getMembresia().getTipo().toString() : "Sin membresía"
        ));
        colFinV.setCellValueFactory(d -> new SimpleStringProperty(
                d.getValue().getMembresia() != null
                        ? d.getValue().getMembresia().getFechaVencimiento().toString()
                        : "---"
        ));
        colCupoP.setCellValueFactory(d ->
                new SimpleStringProperty(String.valueOf(d.getValue().getCupoMaximo()))
        );
    }


    /* === BOTONES === */

    @FXML
    public void cargarAsistencia() {
        if (gym.isRecep()){
            mostrar("debes ser Administrador para poder ejecutar esta accion");
            return;
        }
        tablaAsistencia.getItems().setAll(admin.generarEstadisticasAsistencia());
    }

    @FXML
    public void cargarIngresos() {
        if (gym.isRecep()){
            mostrar("debes ser Administrador para poder ejecutar esta accion");
            return;
        }
        tablaIngresos.getItems().setAll(admin.generarIngresosPorMembresias());
    }

    @FXML
    public void cargarPopulares() {
        tablaPopulares.getItems().setAll(admin.generarReporteClasesMasPopulares());
    }

    private void mostrar(String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }

    @FXML
    public void cargarActivos() {

        if (!gym.isRecep()) {
            mostrar("Debes ser Recepcionista para esta acción");
            return;
        }

        Recepcionista recep = gym.obtenerRecepcionistaActual();

        tablaActivos.getItems().setAll(recep.obtenerUsuariosActivos());
    }


    @FXML
    public void cargarVencidos() {

        if (!gym.isRecep()) {
            mostrar("Debes ser Recepcionista para esta acción");
            return;
        }

        Recepcionista recep = gym.obtenerRecepcionistaActual();

        tablaVencidos.getItems().setAll(recep.obtenerUsuariosVencidos());
    }



}
