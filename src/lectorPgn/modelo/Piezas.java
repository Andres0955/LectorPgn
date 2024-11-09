package lectorPgn.modelo;

import java.awt.Image;
import javax.swing.ImageIcon;

public abstract class Piezas {
    private int ancho, alto;
    private int fila, columna;
    private char tipo;
    private String color;
    private Image imagen;
    
    public Piezas(char tipo, String color, String ruta, int fila, int columna){
        this.ancho = 60;
        this.alto = 60;
        this.fila = fila;
        this.columna = columna;
        this.tipo = tipo;
        this.color = color;
        this.imagen = new ImageIcon(getClass().getResource(ruta)).getImage();
    }
    
    public abstract boolean esMovimientoValido(Movimiento movimiento);
    
    public int getAncho(){
        return ancho;
    }
    
    public int getAlto(){
        return alto;
    }
    
    public int getFila(){
        return fila;
    }
    
    public int getColumna(){
        return columna;
    }
    
    public char getTipo(){
        return tipo;
    }
    
    public Image getImagen(){
        return imagen;
    }
    
    public String getColor(){
        return color;
    }
    
    public void setPosicion(int fila, int columna){
        this.fila = fila;
        this.columna = columna;
    }
}
