package tablerodeajedrez.modelo;

public class Reina extends Piezas{
    public Reina(String color, String ruta, int fila, int columna){
        super(color, ruta, fila, columna);
        setTipo('Q');
    }

    @Override
   public boolean esMovimientoValido(int filaDestino, int columnaDestino) {
        int deltaFila = Math.abs(filaDestino - getFila());
        int deltaColumna = Math.abs(columnaDestino - getColumna());
        return deltaFila == deltaColumna || getFila() == filaDestino || getColumna() == columnaDestino; // Movimiento en línea recta o diagonal
    }

}
