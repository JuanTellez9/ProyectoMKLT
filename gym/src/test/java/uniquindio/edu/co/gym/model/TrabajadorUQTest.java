package uniquindio.edu.co.gym.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TrabajadorUQTest {

    @Test
    void calcularDescuento_conMembresiaParametro() {

        TrabajadorUQ trab = new TrabajadorUQ(
                "Carlos", "T01", "320", "Dir",
                "01/01/1990", LocalDate.now(), "Docente", "UQ"
        );

        Membresia mem = new Membresia(
                10, 100000, new Date(), new Date(),
                true, "Café", Tipo.MENSUAL, Nivel.BASICO
        );

        double total = trab.calcularDescuento(mem);

        assertEquals(85000, total); // 100000 - 15%
    }

    @Test
    void calcularDescuento_usandoMembresiaDelUsuario() {

        TrabajadorUQ trab = new TrabajadorUQ(
                "María", "T02", "321", "Dir2",
                "02/05/1995", LocalDate.now(), "Administrativa", "UQ"
        );

        Membresia mem = new Membresia(
                11, 60000, new Date(), new Date(),
                true, "Parqueadero", Tipo.MENSUAL, Nivel.PREMIUM
        );

        trab.setMembresia(mem);

        double total = trab.calcularDescuento(null);

        assertEquals(51000, total); // 60000 - 15%
    }

    @Test
    void calcularDescuento_sinMembresia() {

        TrabajadorUQ trab = new TrabajadorUQ(
                "Luis", "T03", "322", "Dir3",
                "03/03/1998", LocalDate.now(), "Asistente", "UQ"
        );

        double total = trab.calcularDescuento(null);

        assertEquals(0, total);
    }
}
