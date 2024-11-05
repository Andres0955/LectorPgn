package tablerodeajedrez.controlador;

import javax.swing.JFrame;
import javax.swing.JPanel;
import tablerodeajedrez.modelo.*;
import tablerodeajedrez.vista.*;


public class Control {
    private Control instancia;
    private JPanel panelActual;
    private JpPartida jpPartida;
    private JFrame frame;
    private JpInicio jpInicio;
    private Tablero tablero;
     
    
    public Control(JFrame frame){
        this.instancia = this;
        this.frame = frame;
        this.jpInicio = new JpInicio(instancia);
        this.tablero = new Tablero();
        this.jpPartida = new JpPartida(instancia, tablero);
        
        
        cambiarPanel("inicio");
        cargarPartida();
    }
    
    public void cambiarPanel(String newPanel) {
        if (panelActual != null) {
            frame.remove(panelActual);
        }
        
        switch(newPanel){
            case "inicio":
                panelActual = jpInicio;
                break;
            case "partida":
                panelActual = jpPartida;
                break;
        }

        frame.add(panelActual); 
        frame.revalidate();
        frame.repaint();
    }
    
    private void cargarPartida(){
        LeerArchivo lector = new LeerArchivo();
        tablero.setMovimientos(lector.leerYcargarArchivo("src/recursos/partidas/partida1.txt"));
        jpPartida.setPosiciones(tablero.getPosiciones());
        
    }
    
    public void avanzar(){
        jpPartida.setPosiciones(tablero.reproducirSiguienteMovimiento());
    }
    
    public void retroceder(){
        jpPartida.setPosiciones(tablero.extraerMatriz());
    }

}
