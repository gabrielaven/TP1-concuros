import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Concurso {
    private String nombre;
    private LocalDate fechaInicioInscripcion;
    private LocalDate fechaFinInscripcion;
    private Set<Participante> participantes;

    public Concurso(String nombre, LocalDate fechaInicioInscripcion, LocalDate fechaFinInscripcion) {
        this.nombre = nombre;
        this.fechaInicioInscripcion = fechaInicioInscripcion;
        this.fechaFinInscripcion = fechaFinInscripcion;
        this.participantes = new HashSet<>();
    }

    public void inscribir(Participante participante, LocalDate fechaInscripcion) {
        validarFechaInscripcion(fechaInscripcion);
        boolean primerDia = fechaInscripcion.equals(fechaInicioInscripcion);
        participante.inscribirse(primerDia);
        participantes.add(participante);
    }

    private void validarFechaInscripcion(LocalDate fechaInscripcion) {
        if (fechaInscripcion.isBefore(fechaInicioInscripcion) || fechaInscripcion.isAfter(fechaFinInscripcion)) {
            throw new IllegalArgumentException("Inscripción fuera del rango permitido.");
        }
    }

    public boolean estaInscrito(Participante participante) {
        return participantes.contains(participante);
    }
}
