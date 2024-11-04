package tablerodeajedrez.modelo;

public class Rey extends Piezas{
    public Rey(String color, String ruta, int fila, int columna){
        super(color, ruta, fila, columna);
        setTipo('K');
    }

    @Override
    public boolean esMovimientoValido(int filaDestino, int columnaDestino) {
        int deltaFila = Math.abs(filaDestino - getFila());
        int deltaColumna = Math.abs(columnaDestino - getColumna());
        return deltaFila <= 1 && deltaColumna <= 1; // Movimiento de una casilla en cualquier dirección
    }
    
}
