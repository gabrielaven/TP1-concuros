package participa;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

public class EnviarMail implements Mail {

    private final String username;
    private final String password;
    private final String host;
    private final String puerto;

    public EnviarMail(String username, String password, String host, String puerto) {
        this.username = username;
        this.password = password;
        this.host = host;
        this.puerto = puerto;
    }

    @Override
    public void enviarCorreo(String destinatario, String asunto, String mensaje) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", this.host);
        props.put("mail.smtp.port", this.puerto);

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("no-reply@tuapp.com"));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            msg.setSubject(asunto);
            msg.setText(mensaje);
            Transport.send(msg);
            System.out.println("📧 Correo enviado correctamente.");
        } catch (MessagingException e) {
            throw new RuntimeException("❌ Error enviando el correo: " + e.getMessage());
        }
    }
}
