package uniquindio.edu.co.gym.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class GimnasioTest {

    Gimnasio gym;

    @BeforeEach
    void reset() {
        gym = Gimnasio.getInstance();

        gym.getListMaquina().clear();
        gym.getListMembresia().clear();
        gym.getListTrabajadorUQ().clear();
        gym.getListEntrenadores().clear();
        gym.getListEstudiante().clear();
        gym.getListExterno().clear();
        gym.getListClases().clear();
        gym.getListRecepcionista().clear();
        gym.getListPagos().clear();
    }

    @Test
    void registrarMaquina() {
        Maquina m = new Maquina("Bicicleta", "M01", "BK-2024", "Cardio");

        gym.registrarMaquina(m);

        assertEquals(1, gym.getListMaquina().size());
        assertEquals("M01", gym.getListMaquina().get(0).getId());
    }

    @Test
    void registrarMembresia() {
        Membresia mem = new Membresia(1, 30000, new Date(), new Date(), true,
                "Proteína", Tipo.MENSUAL, Nivel.BASICO);

        gym.registrarMembresia(mem);

        assertEquals(1, gym.getListMembresia().size());
        assertEquals(1, gym.getListMembresia().get(0).getId());
    }

    @Test
    void registrarTrabajadorUQ() {
        TrabajadorUQ t = new TrabajadorUQ("Laura", "T01", "311", "Dir",
                "01/01/2000", LocalDate.now(), "Docente", "UQ");

        gym.registrarTrabajadorUQ(t);

        assertEquals(1, gym.getListTrabajadorUQ().size());
        assertEquals("T01", gym.getListTrabajadorUQ().get(0).getID());
    }

    @Test
    void registrarEntrenador() {
        Entrenador e = new Entrenador("Luis", "E01", "311", "Dir", "01/01/1990", "Tarde");

        gym.registrarEntrenador(e);

        assertEquals(1, gym.getListEntrenadores().size());
        assertEquals("E01", gym.getListEntrenadores().get(0).getID());
    }

    @Test
    void registrarEstudiante() {
        Estudiante est = new Estudiante("Ana", "S01", "320", "Dir",
                "02/02/2000", LocalDate.now(), "Ing", "3", "Ingeniería");

        gym.registrarEstudiante(est);

        assertEquals(1, gym.getListEstudiante().size());
        assertEquals("S01", gym.getListEstudiante().get(0).getID());
    }

    @Test
    void registrarRecepcionista() {
        Recepcionista r = new Recepcionista("Pedro", "R01", "321", "Dir",
                "03/03/2000", "Noche","");

        gym.registrarRecepcionista(r);

        assertEquals(1, gym.getListRecepcionista().size());
        assertEquals("R01", gym.getListRecepcionista().get(0).getID());
    }

    @Test
    void registrarExterno() {
        Externo ex = new Externo("Carlos", "X01", "322", "Dir",
                "04/04/2000", LocalDate.now(), "Programador");

        gym.registrarExterno(ex);

        assertEquals(1, gym.getListExterno().size());
        assertEquals("X01", gym.getListExterno().get(0).getID());
    }

    @Test
    void agregarAdministrador() {
        Administrador admin = new Administrador("Admin", "A01", "300",
                "Dir", "05/05/2000", "Ing", "123");

        gym.setAdministrador(admin);

        assertNotNull(gym.getAdministrador());
        assertEquals("A01", gym.getAdministrador().getID());
    }

    @Test
    void agregarRecepcionista() {
        Recepcionista r = new Recepcionista("Luis", "R02", "310", "Dir",
                "06/06/2000", "Tarde","");

        gym.agregarRecepcionista(r);

        assertEquals(1, gym.getListRecepcionista().size());
    }

    @Test
    void verificarClase() {
        Entrenador ent = new Entrenador("Luisa", "E20", "314", "Dir",
                "10/10/2000", "Mañana");

        Clase c = new Clase("C01", 10, "8:00", "9:00",
                Semana.LUNES, ClaseGrupal.YOGA, ent);

        gym.registrarClase(c);

        assertTrue(gym.verificarClase(c)); // existe

        Clase c2 = new Clase("C02", 10, "8:00", "9:00",
                Semana.LUNES, ClaseGrupal.YOGA, ent);

        assertFalse(gym.verificarClase(c2)); // no existe
    }

    @Test
    void registrarClase() {
        Entrenador ent = new Entrenador("Luisa", "E20", "314", "Dir",
                "10/10/2000", "Mañana");

        Clase c = new Clase("C01", 15, "7:00", "8:00",
                Semana.MARTES, ClaseGrupal.YOGA, ent);

        gym.registrarClase(c);

        assertEquals(1, gym.getListClases().size());
        assertEquals("C01", gym.getListClases().get(0).getId());
    }

    @Test
    void registrarPagos() {
        Usuario u = new Estudiante("Juan", "S20", "311", "Dir",
                "01/01/2001", LocalDate.now(), "Ing", "5", "UQ");

        Pago p = new Pago("P01", "Pago mensual", 50000, new Date(), u);

        gym.registrarPagos(p);

        assertEquals(1, gym.getListPagos().size());
    }

    @Test
    void obtenerRecepcionistaActual() {

        // id va en 2do parámetro
        Recepcionista r1 = new Recepcionista("Luisa", "R01", "300", "Dir", "F", "Mañana", "");

        gym.agregarRecepcionista(r1);

        UsuarioLogueado.getInstance().setUsuario(r1);

        Recepcionista actual = gym.obtenerRecepcionistaActual();

        assertNotNull(actual);
        assertEquals("R01", actual.getID());
    }


}
