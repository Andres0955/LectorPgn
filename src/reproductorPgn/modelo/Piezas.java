package reproductorPgn.modelo;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Esta clase representa todas las piezas del tablero de ajedrez ya que de ella heredan las piezas como caballo, torre, rey, etc...
 * @author Cesar Acosta
 */
public abstract class Piezas {
    private int ancho, alto;
    private int fila, columna;
    private char tipo;
    private String color;
    private Image imagen;
    
   /**
     * Constructor de la clase Piezas. El ancho y el alto de cada pieza es por defecto 60.
     * @param tipo el identificador de la pieza, en este caso 'B'.
     * @param color el color que tendra la pieza.
     * @param ruta la ruta en la que se encuentra el png de la pieza.
     * @param fila la fila inicial donde se ubicara dentro de la matriz.
     * @param columna la columna inicial donde se ubicara dentro de la matriz.
     */
    public Piezas(char tipo, String color, String ruta, int fila, int columna){
        this.ancho = 60;
        this.alto = 60;
        this.fila = fila;
        this.columna = columna;
        this.tipo = tipo;
        this.color = color;
        this.imagen = new ImageIcon(getClass().getResource(ruta)).getImage();
    }
    
    /**
     * Metodo abstracto para validar si es un movimiento valido para las piezas.
     * @param movimiento instancia de Moviento en la que se encuentra la ubicacion a la que se desea mover.
     * @return true si es un moviminto valido dentro de las reglas del ajedrez, false si no es valido.
     */
    public abstract boolean esMovimientoValido(Movimiento movimiento);
    
    /**
     * Obtiene el ancho de la pieza.
     * @return el ancho de la pieza.
     */
    public int getAncho(){
        return ancho;
    }
    
    /**
     * obtiene el alto de la pieza.
     * @return el alto de la pieza.
     */
    public int getAlto(){
        return alto;
    }
    
    /**
     * obtiene la fila en la que se encuentra la pieza.
     * @return la fila actual de la pieza.
     */
    public int getFila(){
        return fila;
    }
    
    /**
     * Obtiene la columna en la que se encuentra la pieza.
     * @return la columna actual de la pieza.
     */
    public int getColumna(){
        return columna;
    }
    
    /**
     * Obtienen cual es el tipo de la pieza.
     * @return el tipo de la pieza.
     */
    public char getTipo(){
        return tipo;
    }
    
    /**
     * Obtiene la imagen que tiene selecionada de la pieza.
     * @return la imagen de la pieza.
     */
    public Image getImagen(){
        return imagen;
    }
    
    /**
     * Obiente el color que posee la pieza.
     * @return el color de la pieza.
     */
    public String getColor(){
        return color;
    }
    
    /**
     * Modifica o actualiza la posicion de la pieza dentro de la matriz.
     * @param fila fila destino.
     * @param columna columna destino.
     */
    public void setPosicion(int fila, int columna){
        this.fila = fila;
        this.columna = columna;
    }
}
