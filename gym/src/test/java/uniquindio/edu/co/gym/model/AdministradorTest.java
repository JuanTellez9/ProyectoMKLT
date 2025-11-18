package uniquindio.edu.co.gym.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AdministradorTest {

    Administrador admin;
    Gimnasio gym;

    @BeforeEach
    void reset() {
        gym = Gimnasio.getInstance();
        gym.getListEntrenadores().clear();
        gym.getListClases().clear();
        gym.getListPagos().clear();

        admin = new Administrador(
                "Admin", "A01", "3000000000",
                "UQ", "01/01/2000",
                "Ing", "1234"
        );
    }

    // --------------------------------------------------------------------
    // ✔ 1. Verificar entrenador (existe / no existe)
    // --------------------------------------------------------------------
    @Test
    void verificarEntrenador() {

        Entrenador e1 = new Entrenador("Luis", "E01", "311", "Dir", "01/01/2001", "Tarde");
        gym.registrarEntrenador(e1);

        assertTrue(admin.verificarEntrenador(e1));   // debe existir

        Entrenador e2 = new Entrenador("Ana", "E02", "322", "Dir2", "02/02/2002", "Mañana");
        assertFalse(admin.verificarEntrenador(e2));  // no existe
    }

    // --------------------------------------------------------------------
    // ✔ 2. Registrar entrenador (solo si NO existe)
    // --------------------------------------------------------------------
    @Test
    void registrarEntrenador() {

        Entrenador e1 = new Entrenador("Luis", "E01", "311", "Dir", "01/01/2001", "Noche");

        admin.registrarEntrenador(e1);

        assertEquals(1, gym.getListEntrenadores().size());
        assertEquals("E01", gym.getListEntrenadores().get(0).getID());

        // intentar registrar el mismo → NO debe duplicarse
        admin.registrarEntrenador(e1);

        assertEquals(1, gym.getListEntrenadores().size()); // sigue siendo 1
    }

    // --------------------------------------------------------------------
    // ✔ 3. Modificar entrenador
    // --------------------------------------------------------------------
    @Test
    void modificarEntrenador() {

        Entrenador e1 = new Entrenador("Luis", "E01", "300", "Dir", "01/01/2001", "Tarde");
        gym.registrarEntrenador(e1);

        admin.modificarEntrenador("E01", "999", "NuevaDir", "Mañana");

        Entrenador actualizado = gym.getListEntrenadores().get(0);

        assertEquals("999", actualizado.getTelefono());
        assertEquals("NuevaDir", actualizado.getDireccion());
        assertEquals("Mañana", actualizado.getTurno());
    }

    // --------------------------------------------------------------------
    // ✔ 4. Eliminar entrenador
    // --------------------------------------------------------------------
    @Test
    void eliminarEntrenador() {
        Entrenador e1 = new Entrenador("Luis", "E01", "300", "Dir", "01/01/2001", "Noche");
        gym.registrarEntrenador(e1);

        admin.eliminarEntrenador("E01");

        assertEquals(0, gym.getListEntrenadores().size());
    }

    // --------------------------------------------------------------------
    // ✔ 5. Reporte asistencia
    // --------------------------------------------------------------------
    @Test
    void generarEstadisticasAsistencia() {

        Clase c = new Clase("C01", 10, "8:00", "9:00",
                Semana.LUNES, ClaseGrupal.YOGA,
                new Entrenador("Luis", "E01", "300", "Dir", "12/12/2000", "Tarde"));

        gym.registrarClase(c);

        ArrayList<Administrador.ReporteAsistencia> reporte = admin.generarEstadisticasAsistencia();

        assertEquals(1, reporte.size());
        assertEquals("10", reporte.get(0).getCupo());
        assertEquals("0", reporte.get(0).getAsistentes());
    }

    // --------------------------------------------------------------------
    // ✔ 6. Reporte ingresos
    // --------------------------------------------------------------------
    @Test
    void generarIngresosPorMembresias() {

        Usuario u = new Estudiante("Juan", "S01", "300", "Dir", "01/01/2000",
                java.time.LocalDate.now(), "Ing", "2", "UQ");

        gym.registrarPagos(new Pago("1", "Pago 1", 100000, new Date(), u));
        gym.registrarPagos(new Pago("2", "Pago 2", 50000, new Date(), u));

        Administrador.ReporteIngresos r = admin.generarIngresosPorMembresias();

        assertEquals("2", r.getCantidad());
        assertEquals("$150000.0", r.getTotal());
    }

    // --------------------------------------------------------------------
    // ✔ 7. Reporte clases más populares
    // --------------------------------------------------------------------
    @Test
    void generarReporteClasesMasPopulares() {

        Clase c1 = new Clase("C01", 10, "8:00", "9:00", Semana.LUNES,
                ClaseGrupal.YOGA, new Entrenador("A", "E1", "3", "D", "F", "T"));

        Clase c2 = new Clase("C02", 10, "8:00", "9:00", Semana.MARTES,
                ClaseGrupal.YOGA, new Entrenador("B", "E2", "3", "D", "F", "T"));

        c1.inscribirUsuario(new Estudiante("Juan", "S01", "300", "Dir", "F",
                java.time.LocalDate.now(), "Ing", "2", "UQ"));

        gym.registrarClase(c1);
        gym.registrarClase(c2);

        ArrayList<Clase> orden = admin.generarReporteClasesMasPopulares();

        assertEquals("C01", orden.get(0).getId()); // tiene 1 inscrito
        assertEquals("C02", orden.get(1).getId()); // tiene 0 inscritos
    }
}
