package tablerodeajedrez;

import javax.swing.JFrame;
import tablerodeajedrez.controlador.Control;

public class Main {
    
    public static void main(String[] args) {
      JFrame frame = new JFrame("Partida de Ajedrez");
      Control control = new Control(frame);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
      frame.pack();
    }
    
}
