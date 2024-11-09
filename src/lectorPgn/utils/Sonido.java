package lectorPgn.utils;

import javax.sound.sampled.*;
import java.io.IOException;

public class Sonido {
    private Clip clip;  
    
    public Sonido(){
        
    }   
    
    
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

    public void detener() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.flush();
            clip.close();
        }
    }

}
