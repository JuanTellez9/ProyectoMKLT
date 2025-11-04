package uniquindio.edu.co.gym.model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public interface Ihashes {
    byte[] hashearContrasenaBytes(String contrasena);
}
