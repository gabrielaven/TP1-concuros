package participa;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class ArchivoInscripto implements RegistroInscriptos {
    private final Path path;

    public ArchivoInscripto(String rutaArchivo) {
        this.path = Paths.get(rutaArchivo);
    }

    @Override
    public void registrar(LocalDate fecha, int idParticipante, int idConcurso) {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String linea = String.format("%s, %d, %d", fecha.format(formatter), idParticipante, idConcurso);
        try {
            Files.write(path, Arrays.asList(linea), StandardCharsets.UTF_8,
                Files.exists(path) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException("Error al escribir en el archivo: " + e.getMessage(), e);
        }
    }
}
