package uniquindio.edu.co.gym.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import uniquindio.edu.co.gym.model.*;

import java.time.format.DateTimeFormatter;


import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

public class PersonasController {
    private final Gimnasio gimnasio= Gimnasio.getInstance();
    private final ObservableList<Persona> listPersonas= FXCollections.observableArrayList();
    private final ObservableList<Recepcionista> listRecepcionistas= FXCollections.observableArrayList();
    private final ObservableList<Entrenador> listEntrenador= FXCollections.observableArrayList();


    //Recepcionista:
    @FXML private TextField textNombreR;
    @FXML private TextField textIdR;
    @FXML private TextField textTelefonoR;
    @FXML private TextField textTurnoR;
    @FXML private TextField textDireccionR;
    @FXML private DatePicker textFechaNR;
    @FXML private TextField textContrasenaR;
    @FXML private TableView<Recepcionista> tablaRecepcionistas;
    @FXML private TableColumn<Recepcionista, String> colNombreR;
    @FXML private TableColumn<Recepcionista, String> colIdR;
    @FXML private TableColumn<Recepcionista, String> colTelefonoR;
    @FXML private TableColumn<Recepcionista, String> colTurnoR;
    @FXML private TableColumn<Recepcionista, String> colDireccionR;
    @FXML private TableColumn<Recepcionista, String> colFechaNR;

    //Entrenador
    @FXML private TextField textNombreE;
    @FXML private TextField textIdE;
    @FXML private TextField textTelefonoE;
    @FXML private TextField textTurnoE;
    @FXML private TextField textDireccionE;
    @FXML private DatePicker textFechaNE;
    @FXML private TableView<Entrenador>  tablaEntrenador;
    @FXML private TableColumn<Entrenador, String> colNombreE;
    @FXML private TableColumn<Entrenador, String> colIdE;
    @FXML private TableColumn<Entrenador, String> colTelefonoE;
    @FXML private TableColumn<Entrenador, String> colTurnoE;
    @FXML private TableColumn<Entrenador, String> colDireccionE;
    @FXML private TableColumn<Entrenador, String> colFechaNE;


    @FXML
    public void initialize(){
        //Recepcionista:
        colNombreR.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getNombre()));
        colIdR.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getID())));
        colTelefonoR.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getTelefono())));
        colTurnoR.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getTurno())));
        colDireccionR.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getDireccion())));
        colFechaNR.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getFechaNacimiento())));
        listRecepcionistas.setAll(gimnasio.getListRecepcionista());
        tablaRecepcionistas.setItems(listRecepcionistas);

        //Entrenador
        colNombreE.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getNombre()));
        colIdE.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getID())));
        colTelefonoE.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getTelefono())));
        colTurnoE.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getTurno())));
        colDireccionE.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getDireccion())));
        colFechaNE.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getFechaNacimiento())));
        listEntrenador.setAll(gimnasio.getListEntrenadores());
        tablaEntrenador.setItems(listEntrenador);

        //


    }
    @FXML
    private void guardarRecepcionista(){

            String nombreR=textNombreR.getText();
            String idR=textIdR.getText();
            String telefonoR=textTelefonoR.getText();
            String turnoR=textTurnoR.getText();
            String direccionR=textDireccionR.getText();
        LocalDate fechaSeleccionada = textFechaNR.getValue();
        String fechaNRf="";
        if (fechaSeleccionada != null) {
            // Define el formato deseado, por ejemplo: "dd/MM/yyyy"
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            // Convierte la fecha a texto
             fechaNRf = fechaSeleccionada.format(formatter);

            System.out.println("Fecha formateada: " + fechaNRf);
        } else {
            System.out.println("No se ha seleccionado una fecha.");
        }

        String contrasenaR=textContrasenaR.getText();

            Recepcionista nuevo= new Recepcionista(
                    nombreR,
                    idR,
                    telefonoR,
                    direccionR,
                    fechaNRf,
                    turnoR,
                    contrasenaR
            );
            Gimnasio gimnasio= Gimnasio.getInstance().registrarRecepcionista(nuevo);
            listRecepcionistas.add(nuevo);

            limpiarPersona();

    }

    @FXML
    private void guardarEntrenador(){

        String nombreE=textNombreE.getText();
        String idE=textIdE.getText();
        String telefonoE=textTelefonoE.getText();
        String turnoE=textTurnoE.getText();
        String direccionE=textDireccionE.getText();
        LocalDate fechaSeleccionada = textFechaNE.getValue();
        String fechaNEf="";
        if (fechaSeleccionada != null) {
            // Define el formato deseado, por ejemplo: "dd/MM/yyyy"
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            // Convierte la fecha a texto
            fechaNEf = fechaSeleccionada.format(formatter);

            System.out.println("Fecha formateada: " + fechaNEf);
        } else {
            System.out.println("No se ha seleccionado una fecha.");
        }

            Entrenador nuevo= new Entrenador(
                    nombreE,
                    idE,
                    telefonoE,
                    direccionE,
                    fechaNEf,
                    turnoE
            );
        Gimnasio gimnasio= Gimnasio.getInstance().registrarEntrenador(nuevo);
        listEntrenador.add(nuevo);

        limpiarPersona();
    }
    @FXML
    private void onGuardarPersona(){

    }

    @FXML
    private void limpiarPersona(){
        //Recepcionista
        textNombreR.clear();
        textIdR.clear();
        textTelefonoR.clear();
        textTurnoR.clear();
        textDireccionR.clear();
        textFechaNR.setValue(null);
        textContrasenaR.clear();
        //Entrenador
        textNombreE.clear();
        textIdE.clear();
        textTelefonoE.clear();
        textTurnoE.clear();
        textDireccionE.clear();
        textFechaNE.setValue(null);
    }
}
