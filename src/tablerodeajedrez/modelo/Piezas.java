package tablerodeajedrez.modelo;

import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;

public abstract class Piezas {
    private int ancho, alto;
    private int posX, posY;
    private Image imagen;
    
    public Piezas(String ruta){
        this.ancho = 60;
        this.alto = 60;
        this.posX = 0;
        this.posY = 0;
        this.imagen = new ImageIcon(getClass().getResource(ruta)).getImage();
    }
    
    public Piezas(int ancho, int alto, int posX, int posY, String ruta){
        this.ancho = ancho;
        this.alto = alto;
        this.posX = posX;
        this.posY = posY;
        this.imagen = new ImageIcon(getClass().getResource(ruta)).getImage();
    }
    
    public int getAncho(){
        return ancho;
    }
    
    public int getAlto(){
        return alto;
    }
    
    public int getPosX(){
        return posX;
    }
    
    public int getPosY(){
        return posY;
    }
    
    public Image getImagen(){
        return imagen;
    }
    
    public void setPosicion(Point posicion){
        this.posX = posicion.x;
        this.posY = posicion.y;
    }
}
