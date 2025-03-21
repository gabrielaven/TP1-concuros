import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConcursoTest {
    private Concurso concurso;
    private Participante participante;

    @BeforeEach
    void iniciarDatos() {
        LocalDate inicio = LocalDate.of(2025, 3, 1);
        LocalDate fin = LocalDate.of(2025, 3, 10);
        concurso = new Concurso("Concurso de Programación", inicio, fin);
        participante = new Participante("Gabriel Avendaño");
    }

    @Test
    void testInscripcionExitosa() {
        concurso.inscribir(participante, LocalDate.of(2025, 3, 10));
        assertTrue(concurso.estaInscrito(participante));
    }

    @Test
    void testInscripcionPrimerDiaConPuntos() {
        concurso.inscribir(participante, LocalDate.of(2025, 3, 1));
        assertTrue(participante.tienePuntos(10)); 
    }

    @Test
    void testInscripcionFueraDeFecha() {
        Exception excepcion = assertThrows(IllegalArgumentException.class, () -> {
            concurso.inscribir(participante, LocalDate.of(2025, 2, 28));
        });
        assertEquals("Inscripción fuera del rango permitido.", excepcion.getMessage());
        
        excepcion = assertThrows(IllegalArgumentException.class, () -> {
            concurso.inscribir(participante, LocalDate.of(2025, 3, 11));
        });
        assertEquals("Inscripción fuera del rango permitido.", excepcion.getMessage());
    }
}
