package uniquindio.edu.co.gym.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class MembresiaTest {

    @Test
    void registrarUsuario() {
        Membresia m = new Membresia(
                1,
                30000,
                new Date(),
                new Date(),
                true,
                "Proteína",
                Tipo.MENSUAL,
                Nivel.BASICO
        );

        Usuario u = new Estudiante("Ana", "S01", "300", "Dir",
                "01/01/2000", java.time.LocalDate.now(), "Ing", "2", "UQ");

        m.registrarUsuario(u);

        assertEquals(1, m.getListUsuarios().size());
        assertEquals("S01", m.getListUsuarios().get(0).getID());
    }

    @Test
    void isActiva() {
        Date hoy = new Date();
        Date futuro = new Date(hoy.getTime() + 86400000); // +1 día

        Membresia m = new Membresia(
                2,
                40000,
                hoy,
                futuro,
                true,
                "Clases grupales",
                Tipo.ANUAL,
                Nivel.PREMIUM
        );

        assertTrue(m.isActiva());
    }

    @Test
    void isEstado() {
        Membresia m = new Membresia(
                3,
                25000,
                new Date(),
                new Date(),
                true,
                "Toalla",
                Tipo.TRIMESTRAL,
                Nivel.BASICO
        );

        assertTrue(m.isEstado());

        m.setEstado(false);

        assertFalse(m.isEstado());
    }
}
