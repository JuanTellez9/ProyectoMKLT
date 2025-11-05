module uniquindio.edu.co.gym {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;

    // 🔓 Permitir que FXML acceda a tus controladores y clases
    opens uniquindio.edu.co.gym to javafx.fxml;
    opens uniquindio.edu.co.gym.controller to javafx.fxml;

    exports uniquindio.edu.co.gym;
    exports uniquindio.edu.co.gym.controller;
}
