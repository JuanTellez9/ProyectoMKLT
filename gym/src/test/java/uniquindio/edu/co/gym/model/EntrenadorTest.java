package uniquindio.edu.co.gym.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntrenadorTest {

    @Test
    void agregarClase_valida() {
        Entrenador ent = new Entrenador("Luis", "E01", "300", "Dir", "01/01/2000", "Tarde");
        Clase c = new Clase("C01", 20, "8:00", "9:00", Semana.LUNES, ClaseGrupal.YOGA, ent);

        ent.agregarClase(c);

        assertEquals(1, ent.getListaclasesEntrenador().size());
        assertEquals("C01", ent.getListaclasesEntrenador().get(0).getId());
    }

    @Test
    void agregarClase_null() {
        Entrenador ent = new Entrenador("Luis", "E01", "300", "Dir", "01/01/2000", "Tarde");

        // Se captura la excepción interna, pero NO debe agregar nada
        ent.agregarClase(null);

        assertEquals(0, ent.getListaclasesEntrenador().size());
    }

    @Test
    void agregarClase_duplicada() {
        Entrenador ent = new Entrenador("Luis", "E01", "300", "Dir", "01/01/2000", "Tarde");
        Clase c = new Clase("C01", 20, "8:00", "9:00", Semana.LUNES, ClaseGrupal.YOGA, ent);

        ent.agregarClase(c);
        ent.agregarClase(c); // duplicada

        assertEquals(1, ent.getListaclasesEntrenador().size(), "No debe agregar duplicados");
    }
}
