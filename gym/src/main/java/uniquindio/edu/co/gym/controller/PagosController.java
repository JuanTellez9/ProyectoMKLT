package uniquindio.edu.co.gym.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import uniquindio.edu.co.gym.model.Administrador;
import uniquindio.edu.co.gym.model.Gimnasio;
import uniquindio.edu.co.gym.model.Pago;


import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

public class PagosController {
    @FXML
    private TextField textIdPago;
    @FXML
    private DatePicker DatePagoFecha;
    @FXML
    private TextField textValor;
    @FXML
    private TextField textDetallesPago;

    @FXML private TableView<Pago> tablePago;
    @FXML private TableColumn<Pago,String> colID;
    @FXML private TableColumn<Pago, String> colFecha;
    @FXML private TableColumn<Pago, String> colValor;
    @FXML private TableColumn<Pago, String>  colDetallesPago;

    private final Gimnasio gimnasio = Gimnasio.getInstance();
    private final ObservableList<Pago> listPago = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

        colFecha.setCellValueFactory(data -> new SimpleStringProperty(formatoFecha.format(data.getValue().getFechaPago())));
        colID.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getId())));
        colValor.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getValor())));
        colDetallesPago.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getDetallesPago())));

        Collection<Pago> pagos = gimnasio.getListPagos();
        if (pagos != null) {
            listPago.setAll(pagos);
        }
        tablePago.setItems(listPago);
    }
    @FXML
    private void guardarPagos(){
        try{
            String id = textIdPago.getText();
            Date pagoFecha = java.sql.Date.valueOf(DatePagoFecha.getValue().toString());
            int valor = Integer.parseInt(textValor.getText());
            String detalles = textDetallesPago.getText();
            Administrador administrador = gimnasio.getAdministrador();

            Pago nuevo= new Pago(
                    id,
                    detalles,
                    valor,
                    pagoFecha,
                    administrador
            );
            Gimnasio.getInstance().registrarPagos(nuevo);
            listPago.add(nuevo);

            limpiarFormulario();




        }catch(Exception e){
            e.printStackTrace();

        }

    }

    private void limpiarFormulario(){
        textIdPago.clear();
        DatePagoFecha.setValue(null);
        textValor.clear();
        textDetallesPago.clear();
    }


}