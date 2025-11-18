package uniquindio.edu.co.gym.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioLogueadoTest {

    UsuarioLogueado sesion;
    Gimnasio gym;

    @BeforeEach
    void setup() {
        sesion = UsuarioLogueado.getInstance();
        sesion.cerrarSesion(); // siempre comenzar limpio

        gym = Gimnasio.getInstance();

        gym.setAdministrador(null);
        gym.getListRecepcionista().clear();
    }

    // --------------------------------------------------------------
    // 1. VERIFICAR CONTRASEÑA
    // --------------------------------------------------------------
    @Test
    void verificarContrasenaSecure() {

        UsuarioLogueado log = UsuarioLogueado.getInstance();

        // contraseña original
        String original = "12345";

        // hashearla igual que Recepcionista()
        byte[] hashBytes = log.hashearContrasenaBytes(original);
        String hashHex = log.bytesToHex(hashBytes);

        assertTrue(log.verificarContrasenaSecure("12345", hashHex));  // correcta
        assertFalse(log.verificarContrasenaSecure("00000", hashHex)); // incorrecta
    }

    // --------------------------------------------------------------
    // 2. INICIAR SESIÓN COMO ADMINISTRADOR
    // --------------------------------------------------------------
    @Test
    void iniciarSesion_admin() {

        // Crear admin con contraseña en hash
        Administrador admin = new Administrador("Admin", "A01", "300",
                "Dir", "01/01/2000", "Ing", "foto.png");

        // Hashear la contraseña "1234"
        String pass = "1234";
        byte[] hashBytes = sesion.hashearContrasenaBytes(pass);
        admin.setContrasena(sesion.bytesToHex(hashBytes));

        gym.setAdministrador(admin);

        sesion.iniciarSesion("A01", "administrador", "1234");

        assertTrue(sesion.isSesionActiva());
        assertEquals("A01", sesion.getUsuario().getID());
    }

    // --------------------------------------------------------------
    // 3. INICIAR SESIÓN COMO RECEPCIONISTA
    // --------------------------------------------------------------
    @Test
    void iniciarSesion_recepcionista() {

        Recepcionista r = new Recepcionista(
                "Laura", "R01", "300", "Dir",
                "01/01/2000", "Mañana", "1234"
        );

        gym.agregarRecepcionista(r);

        sesion.iniciarSesion("R01", "recepcionista", "1234");

        assertTrue(sesion.isSesionActiva());
        assertEquals("R01", sesion.getUsuario().getID());
    }

    // --------------------------------------------------------------
    // 4. CERRAR SESIÓN
    // --------------------------------------------------------------
    @Test
    void cerrarSesion() {

        Persona p = new Persona("Luis", "P01", "300", "Dir", "01/01/2000");
        sesion.setUsuario(p);

        sesion.cerrarSesion();

        assertFalse(sesion.isSesionActiva());
        assertNull(sesion.getUsuario());
    }

    // --------------------------------------------------------------
    // 5. SESIÓN ACTIVA
    // --------------------------------------------------------------
    @Test
    void isSesionActiva() {

        assertFalse(sesion.isSesionActiva());

        Persona p = new Persona("Luisa", "XX1", "311", "Dir", "02/02/2000");
        sesion.setUsuario(p);

        assertTrue(sesion.isSesionActiva());
    }
}
