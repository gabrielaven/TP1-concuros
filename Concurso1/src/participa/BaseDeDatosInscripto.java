package participa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class BaseDeDatosInscripto implements RegistroInscriptos {

    @Override
    public void registrar(LocalDate fecha, int idParticipante, int idConcurso) {
        String sql = "INSERT INTO inscripciones (fecha, id_participante, id_concurso) VALUES (?, ?, ?)";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, fecha.toString());
            stmt.setInt(2, idParticipante);
            stmt.setInt(3, idConcurso);
            stmt.executeUpdate();

            System.out.println("✅ Inscripción registrada en la base de datos.");
        } catch (SQLException e) {
            throw new RuntimeException("❌ Error al registrar inscripción en la base de datos: " + e.getMessage(), e);
        }
    }
}
