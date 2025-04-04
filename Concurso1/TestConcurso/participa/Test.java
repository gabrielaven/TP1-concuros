package participa;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.nio.file.Path;
import java.nio.file.Paths;

class ConcursoTest {
    private Concurso concurso;
    private Participante participante;
    private ArchivoInscripto archivoInscripto; 
    private final String rutaArchivo = "C:\\Users\\User\\Downloads\\universidad\\objetos 2\\archivos\\inscripciones.txt";
    private final Path path = Paths.get(rutaArchivo);
    private FakeMailTest servicioMensajeriaFake;
    private InscriptosFake inscriptosFake;
    
    @BeforeEach
    void iniciarDatos() {
        LocalDate inicio = LocalDate.of(2025, 3, 1);
        LocalDate fin = LocalDate.of(2025, 3, 10);
        archivoInscripto = new ArchivoInscripto(rutaArchivo);  
        servicioMensajeriaFake = new FakeMailTest();
        inscriptosFake = new InscriptosFake("");
        concurso = new Concurso("Concurso de Programación", inicio, fin, inscriptosFake,servicioMensajeriaFake);
        participante = new Participante("Gabriel Avendaño","gabriel@gmail.com");
 
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

    @Test
    void testInscripcionSeRegistraEnFake() {
        concurso.inscribir(participante, LocalDate.of(2025, 3, 3));

        String esperado = "03/03/2025, ";
        assertTrue(inscriptosFake.data().startsWith(esperado));
    }


    @Test
    void testEnvioCorreoAlInscribirse() {
        concurso.inscribir(participante, LocalDate.of(2025, 3, 5));
        assertTrue(concurso.estaInscrito(participante));
        assertEquals("gabriel@gmail.com - Inscripción: Usted ha realizado la inscripción...", servicioMensajeriaFake.getMail());
    }

}

