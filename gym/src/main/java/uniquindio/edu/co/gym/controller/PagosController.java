package uniquindio.edu.co.gym.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import uniquindio.edu.co.gym.model.Administrador;
import uniquindio.edu.co.gym.model.Gimnasio;
import uniquindio.edu.co.gym.model.Pago;

import java.awt.*;
import java.util.Date;

public class PagosController {
    @FXML
    private TextField textIdPago;
    @FXML
    private DatePicker DatePagoFecha;
    @FXML
    private TextField textValor;
    @FXML
    private TextField textDetalles;

    @FXML private TableView<Pago> tablePago;
    @FXML private TableColumn<Pago,String> colID;
    @FXML private TableColumn<Pago, String> colFecha;
    @FXML private TableColumn<Pago, String> colValor;
    @FXML private TableColumn<Pago, String> colDetalles;

    private final Gimnasio gimnasio = Gimnasio.getInstance();
    private final ObservableList<Pago> listHistorialPago = FXCollections.observableArrayList();

    @FXML
    private void guardarPagos(){
        try{
            String id = textIdPago.getText();
            Date pagoFecha = java.sql.Date.valueOf(DatePagoFecha.getValue().toString());
            int valor = Integer.parseInt(textValor.getText());
            String detalles = textDetalles.getText();
            Administrador administrador = gimnasio.getAdministrador();

            Pago nuevo= new Pago(
                    id,
                    detalles,
                    valor,
                    pagoFecha,
                    administrador
            );
            
            listHistorialPago.add(nuevo);




        }catch(Exception e){

        }

    }
}
