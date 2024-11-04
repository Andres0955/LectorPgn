package tablerodeajedrez.modelo;

public class Torre extends Piezas{
    public Torre(String color, String ruta, int fila, int columna){
        super(color, ruta, fila, columna);
        setTipo('R');
    }

    /**
     *
     * @param filaDestino
     * @param columnaDestino
     * @return
     */
    @Override
    public boolean esMovimientoValido(int filaDestino, int columnaDestino) {
        return getFila() == filaDestino || getColumna() == columnaDestino; // Movimiento en línea recta
    }
}
