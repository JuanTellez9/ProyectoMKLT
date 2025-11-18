package uniquindio.edu.co.gym.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class EstudianteTest {

    @Test
    void calcularDescuento_conMembresiaParametro() {

        Estudiante est = new Estudiante(
                "Juan", "E01", "300", "Dir",
                "01/01/2000", LocalDate.now(),
                "Ing", "3", "Ingeniería"
        );

        Membresia mem = new Membresia(
                10, 100000, new Date(), new Date(),
                true, "Valoración", Tipo.MENSUAL, Nivel.BASICO
        );

        double total = est.calcularDescuento(mem);

        assertEquals(70000, total); // 100000 - 30%
    }

    @Test
    void calcularDescuento_usandoMembresiaDelUsuario() {

        Estudiante est = new Estudiante(
                "Luis", "E02", "301", "Dir",
                "01/01/2000", LocalDate.now(),
                "Adm", "2", "Económicas"
        );

        Membresia mem = new Membresia(
                11, 50000, new Date(), new Date(),
                true, "Proteína", Tipo.MENSUAL, Nivel.BASICO
        );

        est.setMembresia(mem);

        double total = est.calcularDescuento(null);

        assertEquals(35000, total); // 50000 - 30%
    }

    @Test
    void calcularDescuento_sinMembresia() {

        Estudiante est = new Estudiante(
                "Ana", "E03", "302", "Dir",
                "01/01/2000", LocalDate.now(),
                "Medicina", "1", "Salud"
        );

        double total = est.calcularDescuento(null);

        assertEquals(0, total);
    }
}
