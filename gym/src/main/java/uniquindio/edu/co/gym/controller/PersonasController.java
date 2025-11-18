package uniquindio.edu.co.gym.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import uniquindio.edu.co.gym.model.*;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PersonasController {

    private final Gimnasio gimnasio = Gimnasio.getInstance();

    private final ObservableList<Recepcionista> listRecepcionistas = FXCollections.observableArrayList();
    private final ObservableList<Entrenador> listEntrenador = FXCollections.observableArrayList();
    private final ObservableList<Estudiante> listEstudiante = FXCollections.observableArrayList();
    private final ObservableList<TrabajadorUQ> listTrabajadorUQ = FXCollections.observableArrayList();
    private final ObservableList<Externo> listExterno = FXCollections.observableArrayList();

    // ---- Cloudinary ----
    private final Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dqrwt5fo7",
            "api_key", "987922296483194",
            "api_secret", "z9AKH5_ASnlzDCUs_pFk8oQnLh8"
    ));

    // archivo seleccionado para cada tipo
    private File imagenRecepcionista;
    private File imagenEntrenador;
    private File imagenEstudiante;
    private File imagenTrabajador;
    private File imagenExterno;

    // -------- Recepcionista ----------
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

    // -------- Entrenador ----------
    @FXML private TextField textNombreE;
    @FXML private TextField textIdE;
    @FXML private TextField textTelefonoE;
    @FXML private TextField textTurnoE;
    @FXML private TextField textDireccionE;
    @FXML private DatePicker textFechaNE;
    @FXML private TableView<Entrenador> tablaEntrenador;
    @FXML private TableColumn<Entrenador, String> colNombreE;
    @FXML private TableColumn<Entrenador, String> colIdE;
    @FXML private TableColumn<Entrenador, String> colTelefonoE;
    @FXML private TableColumn<Entrenador, String> colTurnoE;
    @FXML private TableColumn<Entrenador, String> colDireccionE;
    @FXML private TableColumn<Entrenador, String> colFechaNE;
    @FXML
    private TextField updateIdE, updateTelE, updateDirE, updateTurnoE;

    // -------- Estudiante ----------
    @FXML private TextField textNombreEs;
    @FXML private TextField textIdEs;
    @FXML private TextField textTelefonoEs;
    @FXML private TextField textDireccionEs;
    @FXML private DatePicker dateFechaNEs;
    @FXML private DatePicker dateFechaCEs;
    @FXML private TextField textProgramaEs;
    @FXML private TextField textSemestreEs;
    @FXML private TextField textFacultadEs;
    @FXML private TableView<Estudiante> tablaEstudiante;
    @FXML private TableColumn<Estudiante, String> colNombreEs;
    @FXML private TableColumn<Estudiante, String> colIdEs;
    @FXML private TableColumn<Estudiante, String> colTelefonoEs;
    @FXML private TableColumn<Estudiante, String> colDireccionEs;
    @FXML private TableColumn<Estudiante, String> colFechaNEs;
    @FXML private TableColumn<Estudiante, String> colFechaCEs;
    @FXML private TableColumn<Estudiante, String> colProgramaEs;
    @FXML private TableColumn<Estudiante, String> colSemestreEs;
    @FXML private TableColumn<Estudiante, String> colFacultadEs;

    // -------- Trabajador UQ ----------
    @FXML private TextField textNombreT;
    @FXML private TextField textIdT;
    @FXML private TextField textTelefonoT;
    @FXML private TextField textDireccionT;
    @FXML private DatePicker dateFechaNT;
    @FXML private DatePicker dateFechaCT;
    @FXML private TextField textCargoT;
    @FXML private TextField textAreaT;
    @FXML private TableView<TrabajadorUQ> tablaTrabajadorUQ;
    @FXML private TableColumn<TrabajadorUQ, String> colNombreT;
    @FXML private TableColumn<TrabajadorUQ, String> colIdT;
    @FXML private TableColumn<TrabajadorUQ, String> colTelefonoT;
    @FXML private TableColumn<TrabajadorUQ, String> colDireccionT;
    @FXML private TableColumn<TrabajadorUQ, String> colFechaNT;
    @FXML private TableColumn<TrabajadorUQ, String> colFechaCT;
    @FXML private TableColumn<TrabajadorUQ, String> colCargoT;
    @FXML private TableColumn<TrabajadorUQ, String> colAreaT;

    // -------- Externo ----------
    @FXML private TextField textNombreEx;
    @FXML private TextField textIdEx;
    @FXML private TextField textTelefonoEx;
    @FXML private TextField textDireccionEx;
    @FXML private DatePicker dateFechaNEx;
    @FXML private DatePicker dateFechaCEx;
    @FXML private TextField textOcupacionEx;
    @FXML private TableView<Externo> tablaExterno;
    @FXML private TableColumn<Externo, String> colNombreEx;
    @FXML private TableColumn<Externo, String> colIdEx;
    @FXML private TableColumn<Externo, String> colTelefonoEx;
    @FXML private TableColumn<Externo, String> colDireccioEx;
    @FXML private TableColumn<Externo, String> colFechaNEx;
    @FXML private TableColumn<Externo, String> colFechaCEx;
    @FXML private TableColumn<Externo, String> colOcupacionEx;


    @FXML private Button btnNuevoRecep;
    @FXML private ProgressIndicator loaderRecepcionista;

    @FXML private Button btnNuevoEntrenador;
    @FXML private ProgressIndicator loaderEntrenador;

    @FXML private Button btnNuevoEstudiante;
    @FXML private ProgressIndicator loaderEstudiante;

    @FXML private Button btnNuevoTrabajador;
    @FXML private ProgressIndicator loaderTrabajador;

    @FXML private Button btnNuevoExterno;
    @FXML private ProgressIndicator loaderExterno;



    // ComboBox membresías
    @FXML private ComboBox<Membresia> comboMembresiaEs;
    @FXML private ComboBox<Membresia> comboMembresiaT;
    @FXML private ComboBox<Membresia> comboMembresiaEx;

    // Columnas para mostrar membresía
    @FXML private TableColumn<Estudiante, String> colMembresiaEs;
    @FXML private TableColumn<TrabajadorUQ, String> colMembresiaT;
    @FXML private TableColumn<Externo, String> colMembresiaEx;


    // ================== INIT ==================
    @FXML
    public void initialize() {
        // Recepcionistas
        colNombreR.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getNombre()));
        colIdR.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getID()));
        colTelefonoR.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getTelefono()));
        colTurnoR.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getTurno()));
        colDireccionR.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getDireccion()));
        colFechaNR.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getFechaNacimiento()));
        listRecepcionistas.setAll(gimnasio.getListRecepcionista());
        tablaRecepcionistas.setItems(listRecepcionistas);

        // Entrenadores
        colNombreE.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getNombre()));
        colIdE.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getID()));
        colTelefonoE.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getTelefono()));
        colTurnoE.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getTurno()));
        colDireccionE.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getDireccion()));
        colFechaNE.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getFechaNacimiento()));
        listEntrenador.setAll(gimnasio.getListEntrenadores());
        tablaEntrenador.setItems(listEntrenador);

        // Estudiantes
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        colNombreEs.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getNombre()));
        colIdEs.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getID()));
        colTelefonoEs.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getTelefono()));
        colDireccionEs.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getDireccion()));
        colFechaNEs.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getFechaNacimiento()));
        colFechaCEs.setCellValueFactory(d -> {
            Date fecha = Date.valueOf(d.getValue().getFechaCreacion());
            String fechaFormateada = fecha != null ? formatoFecha.format(fecha) : "Sin fecha";
            return new SimpleStringProperty(fechaFormateada);
        });
        colProgramaEs.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getPrograma()));
        colSemestreEs.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getSemestre()));
        colFacultadEs.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getFacultad()));
        listEstudiante.setAll(gimnasio.getListEstudiante());
        tablaEstudiante.setItems(listEstudiante);

        // Trabajadores
        colNombreT.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getNombre()));
        colIdT.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getID()));
        colTelefonoT.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getTelefono()));
        colDireccionT.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getDireccion()));
        colFechaNT.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getFechaNacimiento()));
        colFechaCT.setCellValueFactory(d -> {
            Date fecha = Date.valueOf(d.getValue().getFechaCreacion());
            String fechaFormateada = fecha != null ? formatoFecha.format(fecha) : "Sin fecha";
            return new SimpleStringProperty(fechaFormateada);
        });
        colCargoT.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getCargo()));
        colAreaT.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getArea()));
        listTrabajadorUQ.setAll(gimnasio.getListTrabajadorUQ());
        tablaTrabajadorUQ.setItems(listTrabajadorUQ);

        // Externos
        colNombreEx.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getNombre()));
        colIdEx.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getID()));
        colTelefonoEx.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getTelefono()));
        colDireccioEx.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getDireccion()));
        colFechaNEx.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getFechaNacimiento()));
        colFechaCEx.setCellValueFactory(d -> {
            Date fecha = Date.valueOf(d.getValue().getFechaCreacion());
            String fechaFormateada = fecha != null ? formatoFecha.format(fecha) : "Sin fecha";
            return new SimpleStringProperty(fechaFormateada);
        });
        // Estudiantes: mostrar membresía
        colMembresiaEs.setCellValueFactory(d -> {
            Membresia m = d.getValue().getMembresia();
            String texto = (m == null) ? "Sin membresía" : m.getTipo() + " — $" + m.getCosto();
            return new SimpleStringProperty(texto);
        });

// Trabajador UQ
        colMembresiaT.setCellValueFactory(d -> {
            Membresia m = d.getValue().getMembresia();
            String texto = (m == null) ? "Sin membresía" : m.getTipo() + " — $" + m.getCosto();
            return new SimpleStringProperty(texto);
        });

// Externo
        colMembresiaEx.setCellValueFactory(d -> {
            Membresia m = d.getValue().getMembresia();
            String texto = (m == null) ? "Sin membresía" : m.getTipo() + " — $" + m.getCosto();
            return new SimpleStringProperty(texto);
        });


        // === Cargar membresías ===
        ObservableList<Membresia> membresias = FXCollections.observableArrayList(gimnasio.getListMembresia());

        comboMembresiaEs.setItems(membresias);
        comboMembresiaT.setItems(membresias);
        comboMembresiaEx.setItems(membresias);

// Mostrar tipo + precio
        comboMembresiaEs.setCellFactory(list -> new ListCell<>() {
            @Override
            protected void updateItem(Membresia m, boolean empty) {
                super.updateItem(m, empty);
                setText(empty || m == null ? "" : m.getTipo() + " - $" + m.getCosto());
            }
        });
        comboMembresiaEs.setButtonCell(comboMembresiaEs.getCellFactory().call(null));

        comboMembresiaT.setCellFactory(list -> new ListCell<>() {
            @Override
            protected void updateItem(Membresia m, boolean empty) {
                super.updateItem(m, empty);
                setText(empty || m == null ? "" : m.getTipo() + " - $" + m.getCosto());
            }
        });
        comboMembresiaT.setButtonCell(comboMembresiaT.getCellFactory().call(null));

        comboMembresiaEx.setCellFactory(list -> new ListCell<>() {
            @Override
            protected void updateItem(Membresia m, boolean empty) {
                super.updateItem(m, empty);
                setText(empty || m == null ? "" : m.getTipo() + " - $" + m.getCosto());
            }
        });
        comboMembresiaEx.setButtonCell(comboMembresiaEx.getCellFactory().call(null));

        colOcupacionEx.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getOcupacion()));
        listExterno.setAll(gimnasio.getListExterno());
        tablaExterno.setItems(listExterno);
    }

    // ================= SELECTORES DE IMAGEN =================

    private File seleccionarImagen() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Seleccionar imagen");
        fc.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Imágenes", "*.jpg", "*.jpeg", "*.png")
        );
        return fc.showOpenDialog(null);
    }

    @FXML
    private void seleccionarImagenRecepcionista() {
        imagenRecepcionista = seleccionarImagen();
    }

    @FXML
    private void seleccionarImagenEntrenador() {
        imagenEntrenador = seleccionarImagen();
    }

    @FXML
    private void seleccionarImagenEstudiante() {
        imagenEstudiante = seleccionarImagen();
    }

    @FXML
    private void seleccionarImagenTrabajador() {
        imagenTrabajador = seleccionarImagen();
    }

    @FXML
    private void seleccionarImagenExterno() {
        imagenExterno = seleccionarImagen();
    }

    // ================= GUARDAR PERSONAS =================

    @FXML
    private void guardarRecepcionista() {
        btnNuevoRecep.setDisable(true);
        btnNuevoRecep.setText("Guardando...");
        loaderRecepcionista.setVisible(true);

        new Thread(() -> {
        try {
            String nombreR = textNombreR.getText();
            String idR = textIdR.getText();
            String telefonoR = textTelefonoR.getText();
            String turnoR = textTurnoR.getText();
            String direccionR = textDireccionR.getText();
            LocalDate fecha = textFechaNR.getValue();
            String contrasenaR = textContrasenaR.getText();

            String fechaNRf = fecha != null
                    ? fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                    : "";

            Recepcionista nuevo = new Recepcionista(
                    nombreR, idR, telefonoR, direccionR, fechaNRf, turnoR, contrasenaR
            );
            byte[] hash = nuevo.hashearContrasenaBytes(contrasenaR);
            nuevo.setContrasena(nuevo.bytesToHex(hash));

            // si hay imagen seleccionada, subirla y setear foto
            if (imagenRecepcionista != null) {
                var upload = cloudinary.uploader().upload(imagenRecepcionista, ObjectUtils.emptyMap());
                String url = upload.get("secure_url").toString();
                nuevo.setFoto(url);
            }else{
                System.out.println("llego vacio");
            }
            System.out.println("elrecpcionista creado fuer "+ nuevo + " y su imagen de perfil es "+ nuevo.getFoto());
            gimnasio.registrarRecepcionista(nuevo);
            listRecepcionistas.add(nuevo);   // <-- esto actualiza la tabla

            imagenRecepcionista = null;
            limpiarPersona();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Volver al hilo de JavaFX
            javafx.application.Platform.runLater(() -> {
                btnNuevoRecep.setDisable(false);
                btnNuevoRecep.setText("Nuevo");
                loaderRecepcionista.setVisible(false);
            });
        }
        }).start();
    }

    @FXML
    private void guardarEntrenador() {
        if (gimnasio.isRecep()){
            mostrar("debes ser Administrador para poder ejecutar esta accion");
            return;
        }
        btnNuevoEntrenador.setDisable(true);
        btnNuevoEntrenador.setText("Guardando...");
        loaderEntrenador.setVisible(true);

        new Thread(() -> {
            try {
                String nombre = textNombreE.getText();
                String id = textIdE.getText();
                String tel = textTelefonoE.getText();
                String turno = textTurnoE.getText();
                String dir = textDireccionE.getText();
                LocalDate fecha = textFechaNE.getValue();

                String fechaN = fecha != null
                        ? fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                        : "";

                Entrenador nuevo = new Entrenador(
                        nombre, id, tel, dir, fechaN, turno
                );

                if (imagenEntrenador != null) {
                    var upload = cloudinary.uploader().upload(imagenEntrenador, ObjectUtils.emptyMap());
                    nuevo.setFoto(upload.get("secure_url").toString());
                }

                gimnasio.registrarEntrenador(nuevo);
                listEntrenador.add(nuevo);

                imagenEntrenador = null;
                limpiarPersona();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                Platform.runLater(() -> {
                    btnNuevoEntrenador.setDisable(false);
                    btnNuevoEntrenador.setText("Nuevo");
                    loaderEntrenador.setVisible(false);
                });
            }
        }).start();
    }


    @FXML
    private void guardarEstudiante() {
        if (!gimnasio.isRecep()){
            mostrar("debes ser Recepcionista para poder ejecutar esta accion");
            return;
        }
        btnNuevoEstudiante.setDisable(true);
        btnNuevoEstudiante.setText("Guardando...");
        loaderEstudiante.setVisible(true);

        new Thread(() -> {
            try {
                String nombre = textNombreEs.getText();
                String id = textIdEs.getText();
                String tel = textTelefonoEs.getText();
                String dir = textDireccionEs.getText();
                LocalDate fechaN = dateFechaNEs.getValue();
                LocalDate fechaC = dateFechaCEs.getValue();
                String programa = textProgramaEs.getText();
                String semestre = textSemestreEs.getText();
                String facultad = textFacultadEs.getText();

                Membresia memSeleccionada = comboMembresiaEs.getValue();

                if (memSeleccionada == null) {
                    mostrar("Debe seleccionar una membresía");
                    return;
                }

                String fechaNF = fechaN != null
                        ? fechaN.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                        : "";

                Estudiante nuevo = new Estudiante(
                        nombre, id, tel, dir, fechaNF,
                        fechaC, programa, semestre, facultad
                );

                // === FOTO ===
                if (imagenEstudiante != null) {
                    var upload = cloudinary.uploader().upload(imagenEstudiante, ObjectUtils.emptyMap());
                    nuevo.setFoto(upload.get("secure_url").toString());
                }

                // === ASIGNACIÓN DE MEMBRESÍA ===
                ArrayList<Membresia> memArray = gimnasio.getListMembresia();

                for (Membresia m : memArray) {

                    if (m.getId() == memSeleccionada.getId()) {

                        // 1. Asignar la membresía al usuario
                        nuevo.setMembresia(m);

                        // 2. Registrar el usuario dentro de esa membresía
                        m.registrarUsuario(nuevo);

                        break;
                    }
                }

                // guardar lista global actualizada
                gimnasio.setListMembresia(memArray);

                // Registrar estudiante
                gimnasio.registrarEstudiante(nuevo);
                registrarPagoAutomatico(nuevo);
                listEstudiante.add(nuevo);

                imagenEstudiante = null;
                limpiarPersona();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                Platform.runLater(() -> {
                    btnNuevoEstudiante.setDisable(false);
                    btnNuevoEstudiante.setText("Nuevo");
                    loaderEstudiante.setVisible(false);
                });
            }
        }).start();
    }


    @FXML
    private void guardarTrabajadoresUq() {
        if (!gimnasio.isRecep()){
            mostrar("debes ser Recepcionista para poder ejecutar esta accion");
            return;
        }

        btnNuevoTrabajador.setDisable(true);
        btnNuevoTrabajador.setText("Guardando...");
        loaderTrabajador.setVisible(true);

        new Thread(() -> {
            try {
                String nombre = textNombreT.getText();
                String id = textIdT.getText();
                String tel = textTelefonoT.getText();
                String dir = textDireccionT.getText();
                LocalDate fechaN = dateFechaNT.getValue();
                LocalDate fechaC = dateFechaCT.getValue();
                String cargo = textCargoT.getText();
                String area = textAreaT.getText();

                Membresia memSeleccionada = comboMembresiaT.getValue();

                if (memSeleccionada == null) {
                    mostrar("Debe seleccionar una membresía");
                    return;
                }

                String fechaNF = fechaN != null
                        ? fechaN.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                        : "";

                TrabajadorUQ nuevo = new TrabajadorUQ(
                        nombre, id, tel, dir, fechaNF, fechaC, cargo, area
                );

                // Foto
                if (imagenTrabajador != null) {
                    var upload = cloudinary.uploader().upload(imagenTrabajador, ObjectUtils.emptyMap());
                    nuevo.setFoto(upload.get("secure_url").toString());
                }

                // === ASIGNACIÓN DE MEMBRESÍA ===
                ArrayList<Membresia> memArray = gimnasio.getListMembresia();

                for (Membresia m : memArray) {
                    if (m.getId() == memSeleccionada.getId()) {

                        // 1. Asignar membresía al trabajador
                        nuevo.setMembresia(m);

                        // 2. Registrar usuario en la membresía
                        m.registrarUsuario(nuevo);

                        break;
                    }
                }

                // Guardar lista global
                gimnasio.setListMembresia(memArray);

                // Registrar trabajador
                gimnasio.registrarTrabajadorUQ(nuevo);
                registrarPagoAutomatico(nuevo);

                listTrabajadorUQ.add(nuevo);

                imagenTrabajador = null;
                limpiarPersona();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                Platform.runLater(() -> {
                    btnNuevoTrabajador.setDisable(false);
                    btnNuevoTrabajador.setText("Nuevo");
                    loaderTrabajador.setVisible(false);
                });
            }
        }).start();
    }

    @FXML
    private void guardarExterno() {
        if (!gimnasio.isRecep()){
            mostrar("debes ser Recepcionista para poder ejecutar esta accion");
            return;
        }
        btnNuevoExterno.setDisable(true);
        btnNuevoExterno.setText("Guardando...");
        loaderExterno.setVisible(true);

        new Thread(() -> {
            try {
                String nombre = textNombreEx.getText();
                String id = textIdEx.getText();
                String tel = textTelefonoEx.getText();
                String dir = textDireccionEx.getText();
                LocalDate fechaN = dateFechaNEx.getValue();
                LocalDate fechaC = dateFechaCEx.getValue();
                String ocupacion = textOcupacionEx.getText();

                Membresia memSeleccionada = comboMembresiaEx.getValue();

                if (memSeleccionada == null) {
                    mostrar("Debe seleccionar una membresía");
                    return;
                }

                String fechaNF = fechaN != null
                        ? fechaN.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                        : "";

                Externo nuevo = new Externo(
                        nombre, id, tel, dir, fechaNF, fechaC, ocupacion
                );

                if (imagenExterno != null) {
                    var upload = cloudinary.uploader().upload(imagenExterno, ObjectUtils.emptyMap());
                    nuevo.setFoto(upload.get("secure_url").toString());
                }

                // === ASIGNACIÓN DE MEMBRESÍA ===
                ArrayList<Membresia> memArray = gimnasio.getListMembresia();

                for (Membresia m : memArray) {
                    if (m.getId() == memSeleccionada.getId()) {

                        // 1. Asignar membresía al externo
                        nuevo.setMembresia(m);

                        // 2. Registrar usuario dentro de la membresía
                        m.registrarUsuario(nuevo);

                        break;
                    }
                }

                // actualizar lista global
                gimnasio.setListMembresia(memArray);

                // Registrar externo
                gimnasio.registrarExterno(nuevo);
                registrarPagoAutomatico(nuevo);
                listExterno.add(nuevo);

                imagenExterno = null;
                limpiarPersona();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                Platform.runLater(() -> {
                    btnNuevoExterno.setDisable(false);
                    btnNuevoExterno.setText("Nuevo");
                    loaderExterno.setVisible(false);
                });
            }
        }).start();
    }
    private void registrarPagoAutomatico(Usuario usuario) {

        Membresia m = usuario.getMembresia();
        if (m == null) return;

        Pago p = new Pago(usuario.getID(),"Pago de membresía " + m.getTipo(),(int)m.getCosto(),new java.util.Date(),usuario);


        gimnasio.registrarPagos(p);
    }


    // ================= LIMPIAR CAMPOS =================
    @FXML
    private void limpiarPersona() {
        // Recepcionista
        textNombreR.clear(); textIdR.clear(); textTelefonoR.clear();
        textTurnoR.clear(); textDireccionR.clear(); textContrasenaR.clear();
        textFechaNR.setValue(null);

        // Entrenador
        textNombreE.clear(); textIdE.clear(); textTelefonoE.clear();
        textTurnoE.clear(); textDireccionE.clear(); textFechaNE.setValue(null);

        // Estudiante
        textNombreEs.clear(); textIdEs.clear(); textTelefonoEs.clear();
        textDireccionEs.clear(); dateFechaNEs.setValue(null); dateFechaCEs.setValue(null);
        textProgramaEs.clear(); textSemestreEs.clear(); textFacultadEs.clear();

        // Trabajador
        textNombreT.clear(); textIdT.clear(); textTelefonoT.clear();
        textDireccionT.clear(); dateFechaNT.setValue(null); dateFechaCT.setValue(null);
        textCargoT.clear(); textAreaT.clear();

        // Externo
        textNombreEx.clear(); textIdEx.clear(); textTelefonoEx.clear();
        textDireccionEx.clear(); dateFechaNEx.setValue(null); dateFechaCEx.setValue(null);
        textOcupacionEx.clear();
    }


    @FXML
    private void buscarEntrenador() {
        String id = updateIdE.getText();

        Entrenador encontrado = gimnasio.getListEntrenadores()
                .stream()
                .filter(e -> e.getID().equals(id))
                .findFirst()
                .orElse(null);

        if (encontrado == null) {
            mostrar("No se encontró entrenador con ese ID");
            return;
        }

        updateTelE.setText(encontrado.getTelefono());
        updateDirE.setText(encontrado.getDireccion());
        updateTurnoE.setText(encontrado.getTurno());
    }

    @FXML
    private void actualizarEntrenador() {
        if (gimnasio.isRecep()){
            mostrar("debes ser Administrador para poder ejecutar esta accion");
            return;
        }
        String id = updateIdE.getText();
        String nuevoTel = updateTelE.getText();
        String nuevaDir = updateDirE.getText();
        String nuevoTurno = updateTurnoE.getText();

        gimnasio.getAdministrador().modificarEntrenador(id, nuevoTel, nuevaDir, nuevoTurno);

        listEntrenador.setAll(gimnasio.getListEntrenadores()); // refrescar tabla de Registrar

        mostrar("Entrenador actualizado correctamente");
    }

    @FXML
    private TextField deleteIdE;

    @FXML
    private void eliminarEntrenador() {
        if (gimnasio.isRecep()){
            mostrar("debes ser Administrador para poder ejecutar esta accion");
            return;
        }
        String id = deleteIdE.getText();

        gimnasio.getAdministrador().eliminarEntrenador(id);

        listEntrenador.setAll(gimnasio.getListEntrenadores()); // refrescar tabla

        mostrar("Entrenador eliminado");
    }

    private void mostrar(String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }


}
