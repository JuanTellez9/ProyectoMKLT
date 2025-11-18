package uniquindio.edu.co.gym.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import uniquindio.edu.co.gym.model.Gimnasio;
import uniquindio.edu.co.gym.model.Pago;

import java.text.SimpleDateFormat;

public class PagosController {

    @FXML private TableView<Pago> tablePago;
    @FXML private TableColumn<Pago,String> colID;
    @FXML private TableColumn<Pago, String> colFecha;
    @FXML private TableColumn<Pago, String> colValor;
    @FXML private TableColumn<Pago, String> colDetallesPago;

    private final Gimnasio gimnasio = Gimnasio.getInstance();
    private final ObservableList<Pago> listPago = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        colID.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getId()));
        colFecha.setCellValueFactory(d -> new SimpleStringProperty(
                formato.format(d.getValue().getFechaPago())
        ));
        colValor.setCellValueFactory(d -> new SimpleStringProperty(
                "$" + d.getValue().getValor()
        ));
        colDetallesPago.setCellValueFactory(d -> new SimpleStringProperty(
                d.getValue().getDetallesPago()
        ));

        listPago.setAll(gimnasio.getListPagos());
        tablePago.setItems(listPago);
    }
}
