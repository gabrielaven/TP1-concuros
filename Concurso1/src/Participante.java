public class Participante {
    private String nombre;
    private int puntos;

    public Participante(String nombre) {
        this.nombre = nombre;
        this.puntos = 0;
    }

    public void inscribirse(boolean primerDia) {
        if (primerDia) {
            this.puntos += 10;
        }
    }

    public boolean tienePuntos(int cantidad) {
        return this.puntos == cantidad;
    }
}
