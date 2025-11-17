package uniquindio.edu.co.gym.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import uniquindio.edu.co.gym.model.*;

import java.util.ArrayList;

public class ReportesAdminController {

    private final Gimnasio gym = Gimnasio.getInstance();
    private final Administrador admin = gym.getAdministrador();

    /* ==== TABLA 1: Asistencia ==== */
    @FXML private TableView<Clase> tablaAsistencia;
    @FXML private TableColumn<Clase, String> colClaseA;
    @FXML private TableColumn<Clase, String> colCupoA;
    @FXML private TableColumn<Clase, String> colAsistentesA;
    @FXML private TableColumn<Clase, String> colOcupacionA;

    /* ==== TABLA 2: Ingresos ==== */
    @FXML private TableView<ReporteIngresos> tablaIngresos;
    @FXML private TableColumn<ReporteIngresos, String> colCantidadI;
    @FXML private TableColumn<ReporteIngresos, String> colTotalI;

    /* ==== TABLA 3: Populares ==== */
    @FXML private TableView<Clase> tablaPopulares;
    @FXML private TableColumn<Clase, String> colClaseP;
    @FXML private TableColumn<Clase, String> colAsistentesP;
    @FXML private TableColumn<Clase, String> colCupoP;

    @FXML
    public void initialize() {

        /* ---- Tabla Asistencia ---- */
        colClaseA.setCellValueFactory(d ->
                new SimpleStringProperty(d.getValue().getClaseGrupal().toString())
        );

        colCupoA.setCellValueFactory(d ->
                new SimpleStringProperty(String.valueOf(d.getValue().getCupoMaximo()))
        );

        colAsistentesA.setCellValueFactory(d ->
                new SimpleStringProperty(String.valueOf(
                        d.getValue().getListUsario() != null
                                ? d.getValue().getListUsario().size()
                                : 0
                ))
        );

        colOcupacionA.setCellValueFactory(d -> {
            int cupo = d.getValue().getCupoMaximo();
            int asis = (d.getValue().getListUsario() != null)
                    ? d.getValue().getListUsario().size()
                    : 0;

            double porc = (cupo > 0) ? (asis * 100.0 / cupo) : 0;
            return new SimpleStringProperty(String.format("%.2f%%", porc));
        });


        /* ---- Tabla Popularidad ---- */
        colClaseP.setCellValueFactory(d ->
                new SimpleStringProperty(d.getValue().getClaseGrupal().toString())
        );

        colAsistentesP.setCellValueFactory(d ->
                new SimpleStringProperty(String.valueOf(
                        d.getValue().getListUsario() != null
                                ? d.getValue().getListUsario().size()
                                : 0
                ))
        );

        colCupoP.setCellValueFactory(d ->
                new SimpleStringProperty(String.valueOf(d.getValue().getCupoMaximo()))
        );
    }


    /* ========== BOTÓN: CARGAR INGRESOS ========== */
    @FXML
    public void cargarIngresos() {
        ArrayList<Pago> pagos = gym.getListPagos();

        int cantidad = pagos.size();
        double total = pagos.stream().mapToDouble(Pago::getValor).sum();

        tablaIngresos.getItems().setAll(
                new ReporteIngresos(
                        String.valueOf(cantidad),
                        "$" + total
                )
        );
    }

    /* ========== BOTÓN: CARGAR POPULARES ========== */
    @FXML
    public void cargarPopulares() {
        tablaPopulares.getItems().setAll(gym.getListClases());
    }
}
