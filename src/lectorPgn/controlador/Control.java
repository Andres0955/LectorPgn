package lectorPgn.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import lectorPgn.modelo.*;
import lectorPgn.utils.Sonido;
import lectorPgn.vista.*;


public class Control {
    private Control instancia;
    private JPanel panelActual;
    private JpPartida jpPartida;
    private JFrame frame;
    private JpInicio jpInicio;
    private Tablero tablero;
    private Sonido sonido;
    private Timer temporizador;
    private File archivo;
     
    
    public Control(JFrame frame){
        this.instancia = this;
        this.frame = frame;
        this.jpInicio = new JpInicio(instancia);
        this.tablero = new Tablero(instancia);
        this.sonido = new Sonido();
        this.jpPartida = new JpPartida(instancia);
        this.temporizador = new Timer(1000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                avanzar();
                jpPartida.actualizarPanel();
            }
        });
        this.archivo = new File("");
        frame.add(jpInicio);
        this.panelActual = jpInicio;
    }
    
    public void cambiarPanel(String newPanel) {
        if(archivo == null || archivo.getName().trim().isEmpty()){
            jpInicio.errorAlCargarArchivo();
            return;
        }else if(panelActual != null) {
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
    
    public void obtenerArchivo(File archivoSeleccionado){
        this.archivo = archivoSeleccionado;
        cargarPartida();
    }
    private void cargarPartida(){
        LeerArchivo lector = new LeerArchivo();
        tablero.setMovimientos(lector.leerYcargarArchivo(archivo));
        jpPartida.setPosiciones(tablero.getPosiciones());
        
    }
    
    public void avanzar(){
        Piezas[][] posiciones = tablero.reproducirSiguienteMovimiento();
        sonido.reproducir();
        jpPartida.setPosiciones(posiciones);
        
    }
    
    public void retroceder(){
        Piezas[][] posiciones = tablero.extraerMatriz();
        jpPartida.setPosiciones(posiciones);
        jpPartida.actualizarPanel();
        sonido.reproducir();
        
    }
    
    public void reiniciar(){
        tablero.reiniciarPartida();
        jpPartida.actualizarPanel();
        cargarPartida();
    }
    
    public void iniciarTemporizador(){
        temporizador.start();
    }
    
    public void pararTemporizador(){
        temporizador.stop();
    }
    
    public void obtenerInformacion(String informacion){
        jpPartida.actualizarInformacion(informacion);
    }

}
