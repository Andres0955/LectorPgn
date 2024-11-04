package tablerodeajedrez.modelo;

import java.awt.Point;

public class Alfil extends Piezas{
    
    public Alfil(String color, String ruta, int fila, int columna){
        super(color, ruta, fila, columna);
        setTipo('B');
    }
    
    @Override
    public boolean esMovimientoValido(int filaDestino, int columnaDestino) {
        int deltaFila = Math.abs(filaDestino - getFila());
        int deltaColumna = Math.abs(columnaDestino - getColumna());
        return deltaFila == deltaColumna; // Movimiento en diagonal
    }
}
