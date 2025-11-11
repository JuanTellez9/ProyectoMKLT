package uniquindio.edu.co.gym.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import uniquindio.edu.co.gym.model.ClaseGrupal;
import uniquindio.edu.co.gym.model.Nivel;
import uniquindio.edu.co.gym.model.Semana;
import uniquindio.edu.co.gym.model.Tipo;

import java.time.LocalTime;

public class ClasesController {
    @FXML private TextField textNombre;
    @FXML private TextField textID;
    @FXML private Spinner<LocalTime> spinnerHoraInicio;
    @FXML private Spinner<LocalTime> spinnerHoraFin;
    @FXML private TextField textCupoMaximo;
    @FXML private ComboBox<ClaseGrupal> claseGrupal;
    @FXML private ComboBox<Semana> diaSemana;
    @FXML private ComboBox<Nivel> Nivel;
}