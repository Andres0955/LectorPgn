package reproductorPgn.utils;

import javax.sound.sampled.*;
import java.io.IOException;

/**
 * Esta clase representa el control del sonido de las piezas dentro del juego.
 * @author Cesar Acosta
 */
public class Sonido {
    private Clip clip;  
    
    /**
     * Constructor de la clase Sonido.
     */
    public Sonido(){
        
    }   
    
    /**
     * Reproduce el sonido de una pieza moviendose en el tablero.
     */
    public void reproducir() {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/recursos/sonidos/sonidoPieza.wav"));
            
            clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("El sonido se reprodujo");
            e.printStackTrace();
        }
               
    }

    /**
     * Detiene el sonido.
     */
    public void detener() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.flush();
            clip.close();
        }
    }

}
