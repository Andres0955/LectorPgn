package reproductorPgn.modelo;

/**
 * Esta clase representa la pieza Reina de un tablero de ajedrez.
 * @author Cesar Acosta
 */
public class Reina extends Piezas{
    /**
     * Constructor de la clase Reina.
     * @param tipo el identificador de la pieza, en este caso 'Q'.
     * @param color el color que tendra la pieza.
     * @param ruta la ruta en la que se encuentra el png de la pieza.
     * @param fila la fila inicial donde se ubicara dentro de la matriz.
     * @param columna la columna inicial donde se ubicara dentro de la matriz.
     */
    public Reina(char tipo,String color, String ruta, int fila, int columna){
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
        
        int deltaFila = Math.abs(filaDestino - getFila());
        int deltaColumna = Math.abs(columnaDestino - getColumna());
        return deltaFila == deltaColumna || getFila() == filaDestino || getColumna() == columnaDestino; // Movimiento en línea recta o diagonal
    }

}
