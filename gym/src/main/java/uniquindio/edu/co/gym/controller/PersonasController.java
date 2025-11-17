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

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;


import java.time.LocalDate;


public class PersonasController {
    private final Gimnasio gimnasio= Gimnasio.getInstance();
    private final ObservableList<Recepcionista> listRecepcionistas= FXCollections.observableArrayList();
    private final ObservableList<Entrenador> listEntrenador= FXCollections.observableArrayList();
    private final ObservableList<Estudiante> listEstudiante= FXCollections.observableArrayList();
    private final ObservableList<TrabajadorUQ> listTrabajadorUQ= FXCollections.observableArrayList();
    private final ObservableList<Externo> listExterno= FXCollections.observableArrayList();

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

    //Estudiante
    @FXML private TextField textNombreEs;
    @FXML private TextField textIdEs;
    @FXML private TextField textTelefonoEs;
    @FXML private TextField textDireccionEs;
    @FXML private DatePicker dateFechaNEs;
    @FXML private DatePicker dateFechaCEs;
    @FXML private TextField textProgramaEs;
    @FXML private TextField textSemestreEs;
    @FXML private TextField textFacultadEs;
    @FXML private TableView<Estudiante>  tablaEstudiante;
    @FXML private TableColumn<Estudiante, String> colNombreEs;
    @FXML private TableColumn<Estudiante, String> colIdEs;
    @FXML private TableColumn<Estudiante, String> colTelefonoEs;
    @FXML private TableColumn<Estudiante, String> colDireccionEs;
    @FXML private TableColumn<Estudiante, String> colFechaNEs;
    @FXML private TableColumn<Estudiante, String> colFechaCEs;
    @FXML private TableColumn<Estudiante, String> colProgramaEs;
    @FXML private TableColumn<Estudiante, String> colSemestreEs;
    @FXML private TableColumn<Estudiante, String> colFacultadEs;

    //TrabajadoresUq
    @FXML private TextField textNombreT;
    @FXML private TextField textIdT;
    @FXML private TextField textTelefonoT;
    @FXML private TextField textDireccionT;
    @FXML private DatePicker dateFechaNT;
    @FXML private DatePicker dateFechaCT;
    @FXML private TextField textCargoT;
    @FXML private TextField textAreaT;
    @FXML private TableView<TrabajadorUQ>  tablaTrabajadorUQ;
    @FXML private TableColumn<TrabajadorUQ, String> colNombreT;
    @FXML private TableColumn<TrabajadorUQ, String> colIdT;
    @FXML private TableColumn<TrabajadorUQ, String> colTelefonoT;
    @FXML private TableColumn<TrabajadorUQ, String> colDireccionT;
    @FXML private TableColumn<TrabajadorUQ, String> colFechaNT;
    @FXML private TableColumn<TrabajadorUQ, String> colFechaCT;
    @FXML private TableColumn<TrabajadorUQ, String> colCargoT;
    @FXML private TableColumn<TrabajadorUQ, String> colAreaT;

    //Externo
    @FXML private TextField textNombreEx;
    @FXML private TextField textIdEx;
    @FXML private TextField textTelefonoEx;
    @FXML private TextField textDireccionEx;
    @FXML private DatePicker dateFechaNEx;
    @FXML private DatePicker dateFechaCEx;
    @FXML private TextField textOcupacionEx;
    @FXML private TableView<Externo>  tablaExterno;
    @FXML private TableColumn<Externo, String> colNombreEx;
    @FXML private TableColumn<Externo, String> colIdEx;
    @FXML private TableColumn<Externo, String> colTelefonoEx;
    @FXML private TableColumn<Externo, String> colDireccioEx;
    @FXML private TableColumn<Externo, String> colFechaNEx;
    @FXML private TableColumn<Externo, String> colFechaCEx;
    @FXML private TableColumn<Externo, String> colOcupacionEx;



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

        //Estudiante
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        colNombreEs.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getNombre()));
        colIdEs.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getID())));
        colTelefonoEs.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getTelefono())));
        colDireccionEs.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getDireccion())));
        colFechaNEs.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getFechaNacimiento())));
        colFechaCEs.setCellValueFactory(data -> {
            Date fecha = Date.valueOf(data.getValue().getFechaCreacion());
            String fechaFormateada = (fecha != null) ? formatoFecha.format(fecha) : "Sin fecha";
            return new SimpleStringProperty(fechaFormateada);
        });
        colProgramaEs.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPrograma()));
        colSemestreEs.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getSemestre())));
        colFacultadEs.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getFacultad())));
        listEstudiante.setAll(gimnasio.getListEstudiante());
        tablaEstudiante.setItems(listEstudiante);

        //TrabajadoresUq
         colNombreT.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getNombre()));
        colIdT.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getID())));
        colTelefonoT.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getTelefono())));
        colDireccionT.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getDireccion())));
        colFechaNT.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getFechaNacimiento())));
        colFechaCT.setCellValueFactory(data -> {
            Date fecha = Date.valueOf(data.getValue().getFechaCreacion());
            String fechaFormateada = (fecha != null) ? formatoFecha.format(fecha) : "Sin fecha";
            return new SimpleStringProperty(fechaFormateada);
        });
        colCargoT.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getCargo())));
        colAreaT.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getArea())));
        listTrabajadorUQ.setAll(gimnasio.getListTrabajadorUQ());
        tablaTrabajadorUQ.setItems(listTrabajadorUQ);

        //Externo
        colNombreEx.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getNombre()));
        colIdEx.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getID())));
        colTelefonoEx.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getTelefono())));
        colDireccioEx.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getDireccion())));
        colFechaNEx.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getFechaNacimiento())));
        colFechaCEx.setCellValueFactory(data -> {
            Date fecha = Date.valueOf(data.getValue().getFechaCreacion());
            String fechaFormateada = (fecha != null) ? formatoFecha.format(fecha) : "Sin fecha";
            return new SimpleStringProperty(fechaFormateada);
        });
        colOcupacionEx.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getOcupacion())));
        listExterno.setAll(gimnasio.getListExterno());
        tablaExterno.setItems(listExterno);


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
    private void guardarEstudiante(){
        String nombreEs=textNombreEs.getText();
        String idEs=textIdEs.getText();
        String telefonoEs=textTelefonoEs.getText();
        String direccionEs=textDireccionEs.getText();
        LocalDate fechaCEs= Date.valueOf(dateFechaCEs.getValue().toString()).toLocalDate();
        String programaEs=textProgramaEs.getText();
        String semestreEs=textSemestreEs.getText();
        String facultadEs=textFacultadEs.getText();
        LocalDate fechaSeleccionada = dateFechaNEs.getValue();
        String fechaNEsf="";
        if (fechaSeleccionada != null) {
            // Define el formato deseado, por ejemplo: "dd/MM/yyyy"
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            // Convierte la fecha a texto
            fechaNEsf = fechaSeleccionada.format(formatter);

            System.out.println("Fecha formateada: " + fechaNEsf);
        } else {
            System.out.println("No se ha seleccionado una fecha.");
        }


        Estudiante nuevo= new Estudiante(
                nombreEs,
                idEs,
                telefonoEs,
                direccionEs,
                fechaNEsf,
                fechaCEs,
                programaEs,
                semestreEs,
                facultadEs

        );
        Gimnasio gimnasio=Gimnasio.getInstance().registrarEstudiante(nuevo);
        listEstudiante.add(nuevo);

        limpiarPersona();
    }

    @FXML
    private void guardarTrabajadoresUq(){
        String nombreT=textNombreT.getText();
        String idT=textIdT.getText();
        String telefonoT=textTelefonoT.getText();
        String direccionT=textDireccionT.getText();
        LocalDate fechaCT= Date.valueOf(dateFechaCT.getValue().toString()).toLocalDate();
        LocalDate fechaSeleccionada = dateFechaNT.getValue();
        String cargoT=textCargoT.getText();
        String areaT=textAreaT.getText();
        String fechaNTf="";
        if (fechaSeleccionada != null) {
            // Define el formato deseado, por ejemplo: "dd/MM/yyyy"
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            // Convierte la fecha a texto
            fechaNTf = fechaSeleccionada.format(formatter);

            System.out.println("Fecha formateada: " + fechaNTf);
        } else {
            System.out.println("No se ha seleccionado una fecha.");
        }

        TrabajadorUQ nuevo= new TrabajadorUQ(
                nombreT,
                idT,
                telefonoT,
                direccionT,
                fechaNTf,
                fechaCT,
                cargoT,
                areaT
        );
        Gimnasio gimnasio=Gimnasio.getInstance().registrarTrabajadorUq(nuevo);
        listTrabajadorUQ.add(nuevo);
        limpiarPersona();
    }

    @FXML
    private void guardarExterno(){
        String nombreEx=textNombreEx.getText();
        String idEx=textIdEx.getText();
        String telefonoEx=textTelefonoEx.getText();
        String direccionEx=textDireccionEx.getText();
        LocalDate fechaCEx= Date.valueOf(dateFechaCEx.getValue().toString()).toLocalDate();
        LocalDate fechaSeleccionada = dateFechaNEx.getValue();
        String ocupacionEx=textOcupacionEx.getText();
        String fechaNExf="";
        if (fechaSeleccionada != null) {
            // Define el formato deseado, por ejemplo: "dd/MM/yyyy"
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            // Convierte la fecha a texto
            fechaNExf = fechaSeleccionada.format(formatter);

            System.out.println("Fecha formateada: " + fechaNExf);
        } else {
            System.out.println("No se ha seleccionado una fecha.");
        }

        Externo externo= new Externo(
                nombreEx,
                idEx,
                telefonoEx,
                direccionEx,
                fechaNExf,
                fechaCEx,
                ocupacionEx
        );
        Gimnasio gimnasio=Gimnasio.getInstance().registrarExterno(externo);
        listExterno.add(externo);
        limpiarPersona();

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
        //Estudiante
        textNombreEs.clear();
        textIdEs.clear();
        textTelefonoEs.clear();
        textDireccionEs.clear();
        dateFechaCEs.setValue(null);
        dateFechaNEs.setValue(null);
        textProgramaEs.clear();
        textSemestreEs.clear();
        textFacultadEs.clear();
        //TrabajadorUQ
        textNombreT.clear();
        textIdT.clear();
        textTelefonoT.clear();
        textDireccionT.clear();
        dateFechaCT.setValue(null);
        dateFechaNT.setValue(null);
        textCargoT.clear();
        textAreaT.clear();
        //Externo
        textNombreEx.clear();
        textIdEx.clear();
        textTelefonoEx.clear();
        textDireccionEx.clear();
        dateFechaCEx.setValue(null);
        dateFechaNEx.setValue(null);
        textOcupacionEx.clear();

    }
}
