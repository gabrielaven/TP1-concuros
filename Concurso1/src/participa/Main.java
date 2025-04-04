package participa;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Mail mailService = new EnviarMail(
                "b9537c0a4202cd",  
                "102bc5cd02da86",  
                "sandbox.smtp.mailtrap.io",
                "2525"
        );
        BaseDeDatosInscripto inscriptoDB = new BaseDeDatosInscripto();

        try {
            String fecha = "2025-03-29";
            int idParticipante = 123;
            int idConcurso = 456;
            inscriptoDB.registrar(fecha, idParticipante, idConcurso);
            String destinatario = "tu-email@ejemplo.com"; 
            String asunto = "Nueva inscripción";
            String mensaje = "Se ha registrado una nueva inscripción:\n\n" +
                    "📅 Fecha: " + fecha + "\n" +
                    "👤 ID Participante: " + idParticipante + "\n" +
                    "🏆 ID Concurso: " + idConcurso;
            mailService.enviarCorreo(destinatario, asunto, mensaje);
        } catch (SQLException e) {
            System.out.println("❌ Error en la inscripción: " + e.getMessage());
        }
    }
}
