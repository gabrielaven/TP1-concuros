package participa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BaseDeDatosInscripto {
    public void registrar(String fecha, int idParticipante, int idConcurso) throws SQLException {
        String sql = "INSERT INTO inscripciones (fecha, id_participante, id_concurso) VALUES (?, ?, ?)";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, fecha);
            stmt.setInt(2, idParticipante);
            stmt.setInt(3, idConcurso);
            stmt.executeUpdate();

            System.out.println("✅ Inscripción registrada en la base de datos.");
        }
    }
}
