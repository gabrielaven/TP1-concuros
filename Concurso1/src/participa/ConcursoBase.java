package participa;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class ConcursoBase implements Concurso {
    private final String nombre;
    private final LocalDate fechaInicio;
    private final LocalDate fechaFin;
    private final RegistroInscriptos registro;
    private final Set<Participante> inscritos;
    private final Mail servicioMensajeria;

    public ConcursoBase(String nombre, LocalDate fechaInicio, LocalDate fechaFin, RegistroInscriptos registro, Mail servicioMensajeria) {
        if (fechaInicio.isAfter(fechaFin)) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser después de la fecha de fin.");
        }
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.registro = registro;
        this.inscritos = new HashSet<>();
        this.servicioMensajeria = servicioMensajeria;
    }
    @Override
    public void inscribir(Participante participante, LocalDate fechaInscripcion) {
        if (fechaInscripcion.isBefore(fechaInicio) || fechaInscripcion.isAfter(fechaFin)) {
            throw new IllegalArgumentException("Inscripción fuera del rango permitido.");
        }

        inscritos.add(participante);
        registro.registrar(fechaInscripcion, participante.getId(), this.hashCode());

        if (fechaInscripcion.equals(fechaInicio)) {
            participante.sumarPuntos(10);
        }

        servicioMensajeria.enviarCorreo(participante.getEmail(), "Inscripción", "Usted ha realizado la inscripción...");
    }

    @Override
    public String nombre() {
        return nombre;
    }
}


