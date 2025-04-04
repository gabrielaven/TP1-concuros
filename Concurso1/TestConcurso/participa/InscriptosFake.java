package participa;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InscriptosFake implements RegistroInscriptos {
    private String data;

    public InscriptosFake(String data) {
        this.data = data;
    }

	@Override
	public void registrar(LocalDate fecha, int idParticipante, int idConcurso) {
	    StringBuilder sb = new StringBuilder();
        sb.append(formatearFecha(fecha)).append(", ").append(idParticipante).append(", ").append(idConcurso).append(System.lineSeparator());
        this.data += sb.toString();
    }

    private String formatearFecha(LocalDate fechaInscripcion) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaFormateada = fechaInscripcion.format(formatter);
        return fechaFormateada;
    }

    public boolean startWith(String start) {
        return this.data.startsWith(start);
    }

    public String data() {
        return this.data;
    }
}