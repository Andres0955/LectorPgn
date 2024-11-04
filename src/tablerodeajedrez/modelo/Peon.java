package tablerodeajedrez.modelo;

public class Peon extends Piezas{
    public Peon(String color, String ruta, int fila, int columna){
        super(color, ruta, fila, columna);
        setTipo('P');
    }
    
   @Override
   public boolean esMovimientoValido(int filaDestino, int columnaDestino) {
    int direccion = getColor().equals("blanca") ? -1 : 1; // Determina la dirección según el color
    
    if (columnaDestino == getColumna()) {
        // Avanza una casilla en dirección hacia el oponente
        if (filaDestino == getFila() + direccion) {
            return true;
        }
        // Si es el primer movimiento, puede avanzar dos casillas
        if ((getFila() == 6 && getColor().equals("blanca") || getFila() == 1 && getColor().equals("negra"))
            && filaDestino == getFila() + 2 * direccion) {
            return true;
        }
    } else if (Math.abs(columnaDestino - getColumna()) == 1 && filaDestino == getFila() + direccion) {
        // Captura en diagonal
        return true;
    }

    return false;
}
}
