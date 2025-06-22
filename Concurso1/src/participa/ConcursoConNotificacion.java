package participa;

import java.time.LocalDate;

public class ConcursoConNotificacion implements Concurso {
    private final Concurso concursoDecorado;
    private final Mail servicioMensajeria;

    public ConcursoConNotificacion(Concurso concursoDecorado, Mail servicioMensajeria) {
        this.concursoDecorado = concursoDecorado;
        this.servicioMensajeria = servicioMensajeria;
    }

    @Override
    public void inscribir(Participante participante, LocalDate fechaInscripcion) {
        concursoDecorado.inscribir(participante, fechaInscripcion);

        servicioMensajeria.enviarCorreo(
            participante.getEmail(),
            "Inscripción confirmada",
            "Gracias por inscribirte al concurso: " + concursoDecorado.nombre()
        );
    }

    @Override
    public String nombre() {
        return concursoDecorado.nombre();
    }
}
