package tablerodeajedrez.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import tablerodeajedrez.modelo.*;
import tablerodeajedrez.vista.*;


public class Control {
    private Control instancia;
    private JPanel panelActual;
    private JpPartida jpPartida;
    private JFrame frame;
    private JpInicio jpInicio;
    private Tablero tablero;
    private Timer temporizador;
     
    
    public Control(JFrame frame){
        this.instancia = this;
        this.frame = frame;
        this.jpInicio = new JpInicio(instancia);
        this.tablero = new Tablero();
        this.jpPartida = new JpPartida(instancia);
        this.temporizador = new Timer(1000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                avanzar();
                jpPartida.actualizarPanel();
            }
        });
        
        
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
        tablero.setMovimientos(lector.leerYcargarArchivo("src/recursos/partidas/Partida famosa entre Fischer y Spassky, 1972.txt"));
        jpPartida.setPosiciones(tablero.getPosiciones());
        
    }
    
    public void avanzar(){
        Piezas[][] posiciones = tablero.reproducirSiguienteMovimiento();
        jpPartida.setPosiciones(posiciones);
    }
    
    public void retroceder(){
        Piezas[][] posiciones = tablero.extraerMatriz();
        jpPartida.setPosiciones(posiciones);
        jpPartida.repaint();
        
    }
    
    public void iniciarTemporizador(){
        temporizador.start();
    }
    
    public void pararTemporizador(){
        temporizador.stop();
    }

}
