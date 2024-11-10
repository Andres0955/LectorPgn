package reproductorPgn;

import javax.swing.JFrame;
import reproductorPgn.controlador.Control;

public class Main {
    
    public static void main(String[] args) {
      JFrame frame = new JFrame("PGNPlayer");
      Control control = new Control(frame);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setResizable(false);
      frame.setVisible(true);
      frame.pack();
    }
    
}
