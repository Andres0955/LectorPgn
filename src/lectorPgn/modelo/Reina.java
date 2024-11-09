package lectorPgn.modelo;

public class Reina extends Piezas{
    public Reina(char tipo,String color, String ruta, int fila, int columna){
        super(tipo, color, ruta, fila, columna);
    }

    @Override
   public boolean esMovimientoValido(Movimiento movimiento) {
        int filaDestino = movimiento.getFila();
        int columnaDestino = movimiento.getColumna();
        
        int deltaFila = Math.abs(filaDestino - getFila());
        int deltaColumna = Math.abs(columnaDestino - getColumna());
        return deltaFila == deltaColumna || getFila() == filaDestino || getColumna() == columnaDestino; // Movimiento en línea recta o diagonal
    }

}
