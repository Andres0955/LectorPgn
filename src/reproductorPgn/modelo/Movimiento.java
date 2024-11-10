package reproductorPgn.modelo;

/**
 * Esta clase es la encargada de crear un formato para cada movimiento para una validacion de movimientos mas eficaz.
 * @author Cesar Acosta
 */
public class Movimiento {
    private char tipo;
    private int[] casillaDestino;
    private boolean captura;
    private boolean enroque;
    
    /**
     * Constructor de la clase Movimineto.
     * @param tipo el tipo de la pieza que se desea mover.
     * @param casillaDestino arreglo con las ubicaciones de destino.
     * @param captura si el movimiento es una captura.
     */
    public Movimiento(char tipo, int[] casillaDestino, boolean captura) {
        this.tipo = tipo;
        this.casillaDestino = casillaDestino;
        this.captura = captura;
    }
    
    /**
     * Obtiene el tipo de la pieza.
     * @return el tipo de la pieza.
     */
    public char getTipo() {
        return tipo;
    }
    
    /**
     * Obtiene la fila destino de la piza.
     * @return la fila destino.
     */
    public int getFila() {
        return casillaDestino[0];
    }
    
    /**
     * Obtiene la columna destino de la pieza.
     * @return la columna destino.
     */
    public int getColumna() {
        return casillaDestino[1];
    }
    
    /**
     * Obtiene el arreglo con las ubicaciones destino.
     * @return las ubicaciones destino.
     */
    public int[] getCasillaDestino(){
        return casillaDestino;
    }

    /**
     * Comprueba si el movimiento es una captura.
     * @return true si es una captura, false en caso contrario.
     */
    public boolean esCaptura() {
        return captura;
    }
    
    /**
     * Comprueba si el movimiento es un enroque.
     * @return true si es un enroque, false si no lo es.
     */
    public boolean esEnroque(){
        return enroque;
    }
}
