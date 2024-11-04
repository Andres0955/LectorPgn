package tablerodeajedrez.modelo;

import java.awt.Point;

public class Caballo extends Piezas{
    public Caballo(String color, String ruta, int fila, int columna){
        super(color, ruta, fila, columna);
        setTipo('N');
    }
    
    @Override
    public boolean esMovimientoValido(int filaDestino, int columnaDestino) {
        int deltaFila = Math.abs(filaDestino - getFila());
        int deltaColumna = Math.abs(columnaDestino - getColumna());
        return (deltaFila == 2 && deltaColumna == 1) || (deltaFila == 1 && deltaColumna == 2); // Movimiento en "L"
    }
}
