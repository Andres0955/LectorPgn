package reproductorPgn.modelo;

/**
 * Esta clase representa la pieza Peon de un tablero de ajedrez.
 * @author Cesar Acosta
 */
public class Peon extends Piezas{
    
    /**
     * Constructor de la clase Peon.
     * @param tipo el identificador de la pieza, en este caso 'P'.
     * @param color el color que tendra la pieza.
     * @param ruta la ruta en la que se encuentra el png de la pieza.
     * @param fila la fila inicial donde se ubicara dentro de la matriz.
     * @param columna la columna inicial donde se ubicara dentro de la matriz.
     */
    public Peon(char tipo,String color, String ruta, int fila, int columna){
        super(tipo, color, ruta, fila, columna);
    }
    
    /**
     * Verifica si el moviento es correcto para este tipo de pieza.
     * @param movimiento instancia de Moviento en la que se encuentra la ubicacion a la que se desea mover.
     * @return true si es un moviminto valido dentro de las reglas del ajedrez, false si no es valido.
     */
   @Override
   public boolean esMovimientoValido(Movimiento movimiento) {
       int filaDestino = movimiento.getFila();
        int columnaDestino = movimiento.getColumna();
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
        if(movimiento.esCaptura()){
            return true;
        } 
    }

    return false;
}
}
