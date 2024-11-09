package lectorPgn.modelo;

public class Rey extends Piezas{
    public Rey(char tipo,String color, String ruta, int fila, int columna){
        super(tipo, color, ruta, fila, columna);
    }

    @Override
    public boolean esMovimientoValido(Movimiento movimiento) {
        int filaDestino = movimiento.getFila();
        int columnaDestino = movimiento.getColumna();
        
        int deltaFila = Math.abs(filaDestino - getFila());
        int deltaColumna = Math.abs(columnaDestino - getColumna());

        // Movimiento de una casilla en cualquier dirección
        if (deltaFila <= 1 && deltaColumna <= 1) {
            return true;
        }else if (deltaFila == 0 && deltaColumna == 2 && columnaDestino > getColumna()) {
            return true;
        }else if(deltaFila == 0 && deltaColumna == 2 && columnaDestino < getColumna()) {
            return true;
        }

        return false;
    }

    
}
