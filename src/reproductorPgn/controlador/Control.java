package reproductorPgn.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import reproductorPgn.modelo.LeerArchivo;
import reproductorPgn.utils.Sonido;
import reproductorPgn.vista.*;
import reproductorPgn.modelo.*;


/**
 * Esta clase es la que comunica la vista con el modelo, interactuando como intermediario
 * entre ellas.
 * @author Cesar Acosta
 */
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
     
    /**
     * Constructor de la clase Control.
     * @param frame recive una referencia del Jframe para poder cambiar entre los paneles.
     */
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
    
    /**
     * Cambia los paneles.
     * @param newPanel el nombre del panel al que desea cambiar.
     */
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
    
    /**
     * Obtiene el archivo que el usuario selecciono y posteriormente carga la partida.
     * @param archivoSeleccionado el archivo que el usuario selecciono mediante el file chooser.
     */
    public void obtenerArchivo(File archivoSeleccionado){
        this.archivo = archivoSeleccionado;
        cargarPartida();
    }
    
    /**
     * Crea una instancia de la clase LeerArchivo para obtener los movimientos
     * y mandarlos al tablero y al jpPartida para que puedan cargar las piezas 
     * en su posicion predeterminada.
     */
    private void cargarPartida(){
        LeerArchivo lector = new LeerArchivo();
        tablero.setMovimientosPgn(lector.leerYcargarArchivo(archivo));
        jpPartida.setPosiciones(tablero.getPosiciones());
    }
    
    /**
     * Reproduce un movimiento y recibe la matriz actualizada con ese movimiento
     * para luego mandarla a la vista y el usuario pueda visualizar el movimiento.
     */
    public void avanzar(){
        Piezas[][] posiciones = tablero.reproducirSiguienteMovimiento();
        sonido.reproducir();
        jpPartida.setPosiciones(posiciones);
    }
    
    /**
     * Obtiene la matriz que contiene un moviento previo y manda esta matriz a la
     * vista para poder ser visualizada.
     */
    public void retroceder(){
        Piezas[][] posiciones = tablero.extraerMatriz();
        jpPartida.setPosiciones(posiciones);
        jpPartida.actualizarPanel();
        sonido.reproducir();
        
    }
    
    /**
     * Da la orden al modelo de reiniciar las pilas, los arrayList y la matriz para 
     * una nueva partida.
     */
    public void reiniciar(){
        tablero.reiniciarPartida();
        cargarPartida();
        jpPartida.actualizarPanel();
    }
    
   
    /**
     * Inicia el temporizador para iniciar la reproducción automatica.
     */ 
    public void iniciarTemporizador(){
        temporizador.start();
    }
    
    /**
     * Detiene la reproducción automatica.
     */
    public void pararTemporizador(){
        temporizador.stop();
    }
    
    /**
     * Envia a la vista la informacion de cada movimiento(enroque, captura, jaque o jaque mate).
     * @param informacion la informacion que se desea mostrar.
     */
    public void obtenerInformacion(String informacion){
        jpPartida.actualizarInformacion(informacion);
    }

    /**
     * Modifica el contenido del atributo <code>archivo</code>.
     * @param archivo el nuevo valor de archivo.
     */
    public void setArchivo(File archivo){
        this.archivo = archivo;
    }
}
