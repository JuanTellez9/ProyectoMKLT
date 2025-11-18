package uniquindio.edu.co.gym.util;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class EmailUtil {

    public static void enviarCorreo(String destinatario, String asunto, String mensaje) throws MessagingException {

        // --- Configuración del correo ---
        String remitente = "camilogomess10@gmail.com";
        String password = "mcsh mgjt adtp ovuv";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Sesión
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, password);
            }
        });

        // Crear mensaje
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(remitente));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
        msg.setSubject(asunto);
        msg.setText(mensaje);

        // Enviar
        Transport.send(msg);
    }
}
