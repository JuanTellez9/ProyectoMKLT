package uniquindio.edu.co.gym.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IhashesTest implements Ihashes {

    @Test
    void hashearContrasenaBytes() {

        byte[] hash1 = hashearContrasenaBytes("1234");
        byte[] hash2 = hashearContrasenaBytes("1234");

        assertNotNull(hash1);
        assertEquals(32, hash1.length);   // SHA-256 = 32 bytes
        assertArrayEquals(hash1, hash2);  // mismo texto → mismo hash
    }

    @Test
    void bytesToHex() {

        byte[] ejemplo = new byte[]{10, 15, 16};  // valores arbitrarios

        String hex = bytesToHex(ejemplo);

        assertEquals("0a0f10", hex);  // conversión correcta
    }

    @Test
    void hexToBytes() {

        String hex = "0a0f10";

        byte[] bytes = hexToBytes(hex);

        assertArrayEquals(
                new byte[]{10, 15, 16},
                bytes
        );
    }
}
