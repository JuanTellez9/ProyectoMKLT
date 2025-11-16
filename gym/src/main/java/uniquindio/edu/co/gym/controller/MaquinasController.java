package uniquindio.edu.co.gym.controller;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import uniquindio.edu.co.gym.model.Gimnasio;
import uniquindio.edu.co.gym.model.Maquina;
import javafx.event.ActionEvent;



public class MaquinasController {

    @FXML private TextField textNombre;
    @FXML private TextField textId;
    @FXML private TextField textReferencia;
    @FXML private TextField textUso;

    @FXML private TableColumn <Maquina, String> colNombre;
    @FXML private TableColumn <Maquina, String> colId;
    @FXML private TableColumn <Maquina, String> colReferencia;
    @FXML private TableColumn <Maquina, String> colUso;

    @FXML private TableView<Maquina> tableMaquina;


    private final Gimnasio gimnasio = Gimnasio.getInstance();
    private final ObservableList<Maquina> listaMaquinas = FXCollections.observableArrayList();

 @FXML
         public void initialize(){
     colNombre.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getNombre())));
     colId.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getId())));
     colReferencia.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getReferencia())));
     colUso.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getUso())));

     listaMaquinas.setAll(gimnasio.getListMaquina());
     tableMaquina.setItems(listaMaquinas);
 }
@FXML
    public void agregarMaquina (ActionEvent event){
     try{
         String nombre = textNombre.getText();
         String id = textId.getText();
         String referencia = textReferencia.getText();
         String uso = textUso.getText();

         Maquina nueva = new Maquina(
                 nombre,
                 id,
                 referencia,
                 uso
         );

         Gimnasio.getInstance().registrarMaquina (nueva);
         listaMaquinas.add(nueva);

         limpiarFormulario();
     }catch (NumberFormatException e){}
}
private void limpiarFormulario(){
    textNombre.clear();
    textId.clear();
    textReferencia.clear();
    textUso.clear();

}

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Validación");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}