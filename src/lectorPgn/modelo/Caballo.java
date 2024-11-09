package lectorPgn.modelo;

public class Caballo extends Piezas{
    public Caballo(char tipo,String color, String ruta, int fila, int columna){
        super(tipo, color, ruta, fila, columna);
    }
    
    @Override
    public boolean esMovimientoValido(Movimiento movimiento) {
        int filaDestino = movimiento.getFila();
        int columnaDestino = movimiento.getColumna();
        
        int deltaFila = Math.abs(filaDestino - getFila());
        int deltaColumna = Math.abs(columnaDestino - getColumna());
        return (deltaFila == 2 && deltaColumna == 1) || (deltaFila == 1 && deltaColumna == 2);
    }
}
