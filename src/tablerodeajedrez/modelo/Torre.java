package tablerodeajedrez.modelo;

public class Torre extends Piezas{
    private Tablero tablero;
    
    public Torre(char tipo, String color, String ruta, int fila, int columna, Tablero tablero){
        super(tipo, color, ruta, fila, columna);
        this.tablero = tablero;
    }

    @Override
    public boolean esMovimientoValido(Movimiento movimiento) {
        int filaDestino = movimiento.getFila();
        int columnaDestino = movimiento.getColumna();

        // Verificar si el movimiento es en línea recta (horizontal o vertical)
        boolean esLineaRecta = getFila() == filaDestino || getColumna() == columnaDestino;

        // Verificar el camino libre solo si es un movimiento en línea recta
        return esLineaRecta && caminoLibre(filaDestino, columnaDestino);
    }
    
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
