package uniquindio.edu.co.gym.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ExternoTest {

    @Test
    void calcularDescuento_conMembresiaParametro() {

        Externo ex = new Externo(
                "Pedro", "X01", "311", "Dir",
                "01/01/1990", LocalDate.now(), "Programador"
        );

        Membresia mem = new Membresia(
                20, 100000, new Date(), new Date(),
                true, "Proteína", Tipo.MENSUAL, Nivel.PREMIUM
        );

        double total = ex.calcularDescuento(mem);

        assertEquals(50000, total); // 100000 - 50%
    }

    @Test
    void calcularDescuento_usandoMembresiaDelUsuario() {

        Externo ex = new Externo(
                "Laura", "X02", "312", "Dir2",
                "02/02/1995", LocalDate.now(), "Diseñadora"
        );

        Membresia mem = new Membresia(
                21, 60000, new Date(), new Date(),
                true, "Acompañante", Tipo.MENSUAL, Nivel.BASICO
        );

        ex.setMembresia(mem);

        double total = ex.calcularDescuento(null);

        assertEquals(30000, total); // 60000 - 50%
    }

    @Test
    void calcularDescuento_sinMembresia() {

        Externo ex = new Externo(
                "Ana", "X03", "313", "Dir3",
                "03/03/1998", LocalDate.now(), "Chef"
        );

        double total = ex.calcularDescuento(null);

        assertEquals(0, total);
    }
}
