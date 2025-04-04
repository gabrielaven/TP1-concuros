package participa;

public class Participante {
    private static int contador = 1;
    private final int id;
    private final String nombre;
    private final String email; 
    private int puntos;

    public Participante(String nombre, String email) { 
        this.id = contador++;
        this.nombre = nombre;
        this.email = email;
        this.puntos = 0;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {  
        return email;
    }

    public void sumarPuntos(int puntos) {
        this.puntos += puntos;
    }

    public boolean tienePuntos(int puntosEsperados) {
        return this.puntos == puntosEsperados;
    }
}
