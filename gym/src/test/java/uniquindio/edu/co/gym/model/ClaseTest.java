package uniquindio.edu.co.gym.model;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ClaseTest {

    @Test
    void inscribirUsuario_valido() {
        Clase c = new Clase("C01", 2, "8:00", "9:00",
                Semana.LUNES, ClaseGrupal.YOGA,
                new Entrenador("Luis", "E01", "300", "Dir", "01/01/2000", "Tarde"));

        Usuario u = new Estudiante("Ana", "S01", "311", "Dir", "01/01/2001",
                java.time.LocalDate.now(), "Ing", "3", "UQ");

        boolean resultado = c.inscribirUsuario(u);

        assertTrue(resultado);
        assertEquals(1, c.getListUsario().size());
    }

    @Test
    void inscribirUsuario_null() {
        Clase c = new Clase("C01", 2, "8:00", "9:00",
                Semana.LUNES, ClaseGrupal.YOGA,
                new Entrenador("Luis", "E01", "300", "Dir", "01/01/2000", "Tarde"));

        boolean resultado = c.inscribirUsuario(null);

        assertFalse(resultado);
        assertEquals(0, c.getListUsario().size());
    }

    @Test
    void inscribirUsuario_cupoLleno() {
        Clase c = new Clase("C01", 1, "8:00", "9:00",
                Semana.LUNES, ClaseGrupal.YOGA,
                new Entrenador("Luis", "E01", "300", "Dir", "01/01/2000", "Tarde"));

        Usuario u1 = new Estudiante("Ana", "S01", "311", "Dir", "01/01/2001",
                java.time.LocalDate.now(), "Ing", "3", "UQ");

        Usuario u2 = new Estudiante("Pedro", "S02", "311", "Dir", "01/01/2001",
                java.time.LocalDate.now(), "Ing", "3", "UQ");

        c.inscribirUsuario(u1); // cupo = 1 → lleno

        boolean resultado = c.inscribirUsuario(u2);

        assertFalse(resultado);
        assertEquals(1, c.getListUsario().size());
    }

    @Test
    void inscribirUsuario_listaNullInterna() {
        Clase c = new Clase("C01", 2, "8:00", "9:00",
                Semana.LUNES, ClaseGrupal.YOGA,
                new Entrenador("Luis", "E01", "300", "Dir", "01/01/2000", "Tarde"));

        // forzamos lista interna nula
        c.setListUsario(null);

        Usuario u = new Estudiante("Ana", "S01", "311", "Dir", "01/01/2001",
                java.time.LocalDate.now(), "Ing", "3", "UQ");

        boolean resultado = c.inscribirUsuario(u);

        assertTrue(resultado);
        assertEquals(1, c.getListUsario().size());
    }
}
