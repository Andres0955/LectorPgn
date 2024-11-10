package reproductorPgn.modelo;

/**
 * Esta clase representa la pieza Torre de un tablero de ajedrez.
 * @author Cesar Acosta
 */
public class Torre extends Piezas{
    private Tablero tablero;
    
    /**
     * Constructor de la clase Torre.
     * @param tipo el identificador de la pieza, en este caso 'B'.
     * @param color el color que tendra la pieza.
     * @param ruta la ruta en la que se encuentra el png de la pieza.
     * @param fila la fila inicial donde se ubicara dentro de la matriz.
     * @param columna la columna inicial donde se ubicara dentro de la matriz.
     */
    public Torre(char tipo, String color, String ruta, int fila, int columna, Tablero tablero){
        super(tipo, color, ruta, fila, columna);
        this.tablero = tablero;
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

        // Verificar si el movimiento es en línea recta (horizontal o vertical)
        boolean esLineaRecta = getFila() == filaDestino || getColumna() == columnaDestino;

        // Verificar el camino libre solo si es un movimiento en línea recta
        return esLineaRecta && caminoLibre(filaDestino, columnaDestino);
    }
    
    /**
     * Verifica si no hay ninguna pieza dentro del recorrido de un movimiento.
     * @param filaDestino fila destino.
     * @param columnaDestino columna destino.
     * @return true si no hay ninguna pieza en el camino y false en el caso contrario.
     */
    private boolean caminoLibre(int filaDestino, int columnaDestino) {
        int filaActual = getFila();
        int columnaActual = getColumna();

        // Movimiento vertical (misma columna)
        if (columnaActual == columnaDestino) {
            int incremento = filaDestino > filaActual ? 1 : -1; // Determina la dirección del movimiento
            for (int fila = filaActual + incremento; fila != filaDestino; fila += incremento) {
                if (tablero.estaOcupada(fila, columnaActual)) { // Verifica si hay una pieza en el camino
                    return false;
                }
            }
        }
        // Movimiento horizontal (misma fila)
        else if (filaActual == filaDestino) {
            int incremento = columnaDestino > columnaActual ? 1 : -1; // Determina la dirección del movimiento
            for (int columna = columnaActual + incremento; columna != columnaDestino; columna += incremento) {
                if (tablero.estaOcupada(filaActual, columna)) { // Verifica si hay una pieza en el camino
                    return false;
                }
            }
        } else {
            // No es un movimiento válido para la torre (no es ni horizontal ni vertical)
            return false;
        }
        return true; // No hay piezas en el camino, movimiento libre
    }
}
