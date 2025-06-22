package participa;
import java.time.LocalDate;
public interface Concurso {
    void inscribir(Participante participante, LocalDate fechaInscripcion);
    String nombre();
}