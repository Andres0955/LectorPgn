package lectorPgn;

import javax.swing.JFrame;
import lectorPgn.controlador.Control;

public class Main {
    
    public static void main(String[] args) {
      JFrame frame = new JFrame("PGNPlayer");
      Control control = new Control(frame);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
      frame.pack();
    }
    
}
