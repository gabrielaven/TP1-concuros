package participa;

import java.time.LocalDate;

public interface RegistroInscriptos {
    void registrar(LocalDate fecha, int idParticipante, int idConcurso);
}
