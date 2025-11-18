package uniquindio.edu.co.gym.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class RecepcionistaTest {

    Gimnasio gym;
    Recepcionista recep;

    @BeforeEach
    void setup() {
        gym = Gimnasio.getInstance();

        // Reiniciar listas
        gym.getListEstudiante().clear();
        gym.getListTrabajadorUQ().clear();
        gym.getListExterno().clear();
        gym.getListPagos().clear();

        recep = new Recepcionista("Laura", "R01", "300", "Dir", "01/01/2000", "Mañana", "1234");
    }

    @Test
    void verificarUsuario() {
        Estudiante est = new Estudiante("Ana", "S01", "311", "Dir",
                "02/02/2000", LocalDate.now(), "Ing", "1", "UQ");

        gym.registrarEstudiante(est);

        assertTrue(recep.verificarUsuario(est));
    }

    @Test
    void registrarEstudiante() {
        Date hoy = new Date();
        Date futuro = new Date(hoy.getTime() + 86400000);

        Membresia m = new Membresia(1, 30000, hoy, futuro, true, "benef", Tipo.MENSUAL, Nivel.BASICO);
        Estudiante est = new Estudiante("Ana", "S01", "311", "Dir",
                "02/02/2000", LocalDate.now(), "Ing", "1", "UQ");

        recep.registrarEstudiante(est, m);

        assertEquals(1, gym.getListEstudiante().size());
        assertEquals(m, est.getMembresia());
        assertEquals(1, gym.getListPagos().size());
    }

    @Test
    void registrarTrabajadorUQ() {
        Date hoy = new Date();
        Date futuro = new Date(hoy.getTime() + 86400000);

        Membresia m = new Membresia(1, 30000, hoy, futuro, true, "benef", Tipo.MENSUAL, Nivel.BASICO);
        TrabajadorUQ trab = new TrabajadorUQ("Carlos", "T01", "311", "Dir",
                "01/01/1990", LocalDate.now(), "Docente", "UQ");

        recep.registrarTrabajadorUQ(trab, m);

        assertEquals(1, gym.getListTrabajadorUQ().size());
        assertEquals(m, trab.getMembresia());
        assertEquals(1, gym.getListPagos().size());
    }

    @Test
    void validarAcceso() {
        Date hoy = new Date();
        Date futuro = new Date(hoy.getTime() + 86400000);

        Membresia m = new Membresia(1, 30000, hoy, futuro, true, "benef", Tipo.MENSUAL, Nivel.PREMIUM);
        Estudiante est = new Estudiante("Ana", "S01", "311", "Dir",
                "02/02/2000", LocalDate.now(), "Ing", "1", "UQ");

        gym.registrarEstudiante(est);
        est.setMembresia(m);

        Recepcionista.ResultadoAcceso res = recep.validarAcceso("S01");

        assertTrue(res.permitido);
        assertEquals("ACCESO PERMITIDO ✔", res.mensaje);
    }

    @Test
    void registrarExterno() {
        Date hoy = new Date();
        Date futuro = new Date(hoy.getTime() + 86400000);

        Membresia m = new Membresia(1, 30000, hoy, futuro, true, "benef", Tipo.MENSUAL, Nivel.PREMIUM);
        Externo ex = new Externo("Pedro", "X01", "311", "Dir",
                "01/01/1995", LocalDate.now(), "Programador");

        recep.registrarExterno(ex, m);

        assertEquals(1, gym.getListExterno().size());
        assertEquals(m, ex.getMembresia());
        assertEquals(1, gym.getListPagos().size());
    }

    @Test
    void registrarUsuarioEnClase() {
        // Crear membresía válida
        Date hoy = new Date();
        Date futuro = new Date(hoy.getTime() + 86400000);
        Membresia m = new Membresia(1, 30000, hoy, futuro, true, "benef", Tipo.MENSUAL, Nivel.PREMIUM);

        // Usuario Premium
        Estudiante est = new Estudiante("Ana", "S01", "311", "Dir",
                "02/02/2000", LocalDate.now(), "Ing", "1", "UQ");
        est.setMembresia(m);
        gym.registrarEstudiante(est);

        // Clase
        Entrenador ent = new Entrenador("Luis", "E20", "300", "Dir", "01/01/1990", "Tarde");

        Clase clase = new Clase("C01", 10, "8:00", "9:00",
                Semana.LUNES, ClaseGrupal.YOGA, ent);

        String res = recep.registrarUsuarioEnClase("S01", clase);

        assertEquals("OK", res);
        assertEquals(1, clase.getListUsario().size());
    }

    @Test
    void obtenerUsuariosActivos() {
        Date hoy = new Date();
        Date futuro = new Date(hoy.getTime() + 86400000);

        Membresia m = new Membresia(1, 30000, hoy, futuro, true, "benef", Tipo.MENSUAL, Nivel.PREMIUM);
        Estudiante est = new Estudiante("Ana", "S01", "311", "Dir",
                "02/02/2000", LocalDate.now(), "Ing", "1", "UQ");
        est.setMembresia(m);

        gym.registrarEstudiante(est);

        assertEquals(1, recep.obtenerUsuariosActivos().size());
    }

    @Test
    void obtenerUsuariosVencidos() {
        Date hoy = new Date();
        Date ayer = new Date(hoy.getTime() - 86400000);

        Membresia m = new Membresia(1, 30000, ayer, ayer, true, "benef", Tipo.MENSUAL, Nivel.PREMIUM);
        Estudiante est = new Estudiante("Ana", "S01", "311", "Dir",
                "02/02/2000", LocalDate.now(), "Ing", "1", "UQ");
        est.setMembresia(m);

        gym.registrarEstudiante(est);

        assertEquals(1, recep.obtenerUsuariosVencidos().size());
    }
}
