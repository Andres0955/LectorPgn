package lectorPgn.modelo;

public class Peon extends Piezas{
    public Peon(char tipo,String color, String ruta, int fila, int columna){
        super(tipo, color, ruta, fila, columna);
    }
    
   @Override
   public boolean esMovimientoValido(Movimiento movimiento) {
       int filaDestino = movimiento.getFila();
        int columnaDestino = movimiento.getColumna();
    int direccion = getColor().equals("blanca") ? -1 : 1; // Determina la dirección según el color
    
    if (columnaDestino == getColumna()) {
        // Avanza una casilla en dirección hacia el oponente
        if (filaDestino == getFila() + direccion) {
            return true;
        }
        // Si es el primer movimiento, puede avanzar dos casillas
        if ((getFila() == 6 && getColor().equals("blanca") || getFila() == 1 && getColor().equals("negra"))
            && filaDestino == getFila() + 2 * direccion) {
            return true;
        }
    } else if (Math.abs(columnaDestino - getColumna()) == 1 && filaDestino == getFila() + direccion) {
        // Captura en diagonal
        if(movimiento.esCaptura()){
            return true;
        } 
    }

    return false;
}
}
