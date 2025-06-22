package participa;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Mail mailService = new EnviarMail(
                "b9537c0a4202cd",    
                "102bc5cd02da86",    
                "sandbox.smtp.mailtrap.io",
                "2525"
        );

        RegistroInscriptos inscriptoDB = new BaseDeDatosInscripto();
        Concurso concursoBase = new ConcursoBase(
                "BINGO",
                LocalDate.of(2025, 3, 28),
                LocalDate.of(2025, 3, 31),
                inscriptoDB,
                mailService
        );
        Concurso concursoDecorado = new ConcursoConNotificacion(concursoBase, mailService);
        Participante participante = new Participante("Carlos Pérez", "carlos@example.com");

        try {
            concursoDecorado.inscribir(participante, LocalDate.of(2025, 3, 29));
            System.out.println("Participante inscrito correctamente.");
        } catch (Exception e) {
            System.out.println("Otro error: " + e.getMessage());
        }
    }
}

