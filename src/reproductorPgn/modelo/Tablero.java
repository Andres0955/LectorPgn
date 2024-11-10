package reproductorPgn.modelo;

import java.util.ArrayList;
import java.util.Stack;
import reproductorPgn.controlador.Control;

/**
 * Esta clase es la encargada de ejecutar los movimietos, controlar las validaciones y guardar un historial de la partida.
 * @author Cesar Acosta
 */
public class Tablero {
    private Control control;
    private Piezas[][] posiciones;
    private ArrayList<String> movimientosPgn;
    private Stack<Piezas[][]> historialPosiciones;
    private Stack<Piezas[][]> movimientosRevertidos;
    private boolean esTurnoBlancas;
    private boolean cambiarEstado;

    /**
     * Constructor de la clase Tablero.
     * @param control instancia del controlador enviar y recibir datos de la vista.
     */
    public Tablero(Control control) {
        this.control = control;
        this.posiciones = new Piezas[8][8];
        this.movimientosPgn = new ArrayList<>();
        this.historialPosiciones = new Stack<>();
        this.movimientosRevertidos = new Stack<>();
        this.esTurnoBlancas = true;
        this.cambiarEstado = false;
        
        inicializarTablero();
    }

    /**
     * Crea y estable las ubicaciones de las piezas dentro del la matriz <code>posiciones</code>.
     */
    private void inicializarTablero() {
        posiciones[0][0] = new Torre('R', "negra", "/recursos/imagenes/piezas/torreNegro.png", 0, 0, this);
        posiciones[0][1] = new Caballo('N', "negra", "/recursos/imagenes/piezas/caballoNegro.png", 0, 1);
        posiciones[0][2] = new Alfil('B', "negra", "/recursos/imagenes/piezas/alfilNegro.png", 0, 2);
        posiciones[0][3] = new Reina('Q', "negra", "/recursos/imagenes/piezas/reinaNegro.png", 0, 3);
        posiciones[0][4] = new Rey('K', "negra", "/recursos/imagenes/piezas/reyNegro.png", 0, 4);
        posiciones[0][5] = new Alfil('B', "negra", "/recursos/imagenes/piezas/alfilNegro.png", 0, 5);
        posiciones[0][6] = new Caballo('N', "negra", "/recursos/imagenes/piezas/caballoNegro.png", 0, 6);
        posiciones[0][7] = new Torre('R', "negra", "/recursos/imagenes/piezas/torreNegro.png", 0, 7, this);

        for (int i = 0; i < 8; i++) {
            posiciones[1][i] = new Peon('P', "negra", "/recursos/imagenes/piezas/peonNegro.png", 1, i);
        }

        posiciones[7][0] = new Torre('R', "blanca", "/recursos/imagenes/piezas/torreBlanco.png", 7, 0, this);
        posiciones[7][1] = new Caballo('N', "blanca", "/recursos/imagenes/piezas/caballoBlanco.png", 7, 1);
        posiciones[7][2] = new Alfil('B', "blanca", "/recursos/imagenes/piezas/alfilBlanco.png", 7, 2);
        posiciones[7][3] = new Reina('Q', "blanca", "/recursos/imagenes/piezas/reinaBlanco.png", 7, 3);
        posiciones[7][4] = new Rey('K', "blanca", "/recursos/imagenes/piezas/reyBlanco.png", 7, 4);
        posiciones[7][5] = new Alfil('B', "blanca", "/recursos/imagenes/piezas/alfilBlanco.png", 7, 5);
        posiciones[7][6] = new Caballo('N', "blanca", "/recursos/imagenes/piezas/caballoBlanco.png", 7, 6);
        posiciones[7][7] = new Torre('R', "blanca", "/recursos/imagenes/piezas/torreBlanco.png", 7, 7, this);

        for (int i = 0; i < 8; i++) {
            posiciones[6][i] = new Peon('P', "blanca", "/recursos/imagenes/piezas/peonBlanco.png", 6, i);
        }
        
        cargarNuevaMatriz(posiciones);
    }
    
    /**
     * Ejecuta un movimineto, obteniendo un movimiento del arrayList <code>movimientosPgn</code>, analiza el movimientos, 
     * verifica si es valido el movimiento y guarda en el historial la matriz.
     * @return la nueva matriz con las posiciones actualizadas.
     */
    public Piezas[][] reproducirSiguienteMovimiento(){
        if(!movimientosRevertidos.isEmpty()){
            cambiarEstado = true;
            Piezas[][] matriz = movimientosRevertidos.pop();
            cargarNuevaMatriz(matriz);
            return matriz;
        }
        
        String notacion = obtenerMovimientoPgn();
        Movimiento movimiento = analizarMovimiento(notacion);
        cambiarEstado = true;
        
        if(moverPieza(movimiento)){
            esTurnoBlancas = !esTurnoBlancas;
            cargarNuevaMatriz(posiciones);
            
            return posiciones;
        }
        return null;
    }
    
    /**
     * Obtiene un movimiento del arrayList <code>movimientosPgn</code>.
     * @return el moviento en notacion pgn.
     */
    private String obtenerMovimientoPgn(){
        String movimiento = movimientosPgn.get(0);
        movimientosPgn.remove(0);
        
        return movimiento;
    }
    
    /**
     * Analiza el movimientoen notacion pgn y verifica si hay captura, enroque, jaque, jaque mate, victoria, derrota.
     * Finalmente crea una instancia de Movimiento con los datos encontrados.
     * @param notacion movimiento en notacion pgn.
     * @return una instancia de Movimiento.
     */
    private Movimiento analizarMovimiento(String notacion) {
        char tipo = 'P'; 
        int[] casillaDestino = new int[3];
        boolean captura = false;
        boolean enroqueCortoExitoso;
        boolean enroqueLargoExitoso;
        String infoJugada;
        
        // Verifica si es un enroque
        if (notacion.equals("O-O")){
            enroqueCortoExitoso = ejecutarEnroqueCorto();
            control.obtenerInformacion("Kingside Castling!");
            
            if(enroqueCortoExitoso && esTurnoBlancas){
                casillaDestino = new int[]{7, 6, -1};
                return new Movimiento('K', casillaDestino, captura);
            }else if(enroqueCortoExitoso && !esTurnoBlancas){
                casillaDestino = new int[]{0, 6, -1};
                return new Movimiento('K', casillaDestino, captura);
            }
            
            
        } else if(notacion.equals("O-O-O")){
            enroqueLargoExitoso = ejecutarEnroqueLargo();
            control.obtenerInformacion("Queenside Castling!");
            
            if(enroqueLargoExitoso && esTurnoBlancas){
                casillaDestino = new int[]{7, 2, -1};
                return new Movimiento('K', casillaDestino, captura);
            }else if(enroqueLargoExitoso && !esTurnoBlancas){
                casillaDestino = new int[]{0, 2, -1};
                return new Movimiento('K', casillaDestino, captura);
            }
            
            return new Movimiento('K', casillaDestino, captura); 
        }

        // Determina la pieza en base a la primera letra
        if(notacion.charAt(0) >= 'A' && notacion.charAt(0) <= 'Z') {
            tipo = switch (notacion.charAt(0)) {
                case 'K' -> 'K';
                case 'Q' -> 'Q';
                case 'R' -> 'R';
                case 'B' -> 'B';
                case 'N' -> 'N';
                default -> 'P';
            };
            notacion = notacion.substring(1); // El resto es la casilla
        }
        
        // Verifica si hay captura en el movimiento
        if (notacion.contains("x")) {
            infoJugada = "Capture!";
            captura = true;
            notacion = notacion.replace("x", ""); // Quita la 'x'
        }else if(notacion.contains("+")){
            infoJugada = "Check!";
            notacion = notacion.replace("+", "");
        }else if(notacion.contains("#")){
            infoJugada = "Checkmate!";
            notacion = notacion.replace("#", "");
        }else if(notacion.contains("1-0")){
            infoJugada = "White Wins!";
        }else if(notacion.contains("0-1")){
            infoJugada = "Black wins";
        }else if(notacion.contains("1/2-1/2")){
                infoJugada = "Mutual Agreement";
        }else{
            infoJugada = "...";
        }
        
        control.obtenerInformacion(infoJugada);
        casillaDestino = convertirNotacionACoordenadas(notacion);

        return new Movimiento(tipo, casillaDestino, captura);
    }
    
    /**
     * Ejecuta un enroque corto dependiendo del color de la ficha.
     * @return true si el enroque fue exitoso y false si no fue exitoso.
     */
    private boolean ejecutarEnroqueCorto(){
        if (esTurnoBlancas) {
            if (posiciones[7][5] == null && posiciones[7][6] == null) {
                // Mover la torre blanca al enroque corto
                Piezas torre = posiciones[7][7];
                posiciones[7][7] = null;
                posiciones[7][5] = torre;
                torre.setPosicion(7, 5);
                return true;
            }
        } else {
            // Verificar si el enroque es posible (casillas libres)
            if (posiciones[0][5] == null && posiciones[0][6] == null) {
                // Mover la torre negra al enroque corto
                Piezas torre = posiciones[0][7];
                posiciones[0][7] = null;
                posiciones[0][5] = torre;
                torre.setPosicion(0, 5);
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Ejecuta un enroque largo dependiendo del color de la pieza.
     * @return true si el enroque fue exitoso y false si no fue exitoso.
     */
    private boolean ejecutarEnroqueLargo(){
        if (esTurnoBlancas) {
            if (posiciones[7][3] == null && posiciones[7][2] == null) {
                // Mover la torre blanca al enroque corto
                Piezas torre = posiciones[7][0];
                posiciones[7][0] = null;
                posiciones[7][3] = torre;
                torre.setPosicion(7, 3);
                return true;
            }
        } else {
            // Verificar si el enroque es posible (casillas libres)
            if (posiciones[0][3] == null && posiciones[0][2] == null) {
                // Mover la torre negra al enroque corto
                Piezas torre = posiciones[0][0];
                posiciones[0][0] = null;
                posiciones[0][3] = torre;
                torre.setPosicion(0, 3);
                return true;
            }
        }
        
        return false;
    }

    /**
     * Cambia la posicion de una pieza dentro de la matriz que tiene las piezas.
     * @param movimiento instancia de movimiento con la informacion necesaria para efectuar el movimiento.
     * @return true si se cambio la posicion de la pieza y false en caso contrario.
     */
    private boolean moverPieza(Movimiento movimiento) {
        char tipoPieza = movimiento.getTipo();
        int[] casillaDestino = movimiento.getCasillaDestino();
        int filaDestino = casillaDestino[0];
        int columnaDestino = casillaDestino[1];
        String color = esTurnoBlancas ? "blanca" : "negra";
        ArrayList<Piezas> piezasEncontradas;

        piezasEncontradas = buscarPiezaPorTipoYColor(tipoPieza, color, casillaDestino);

        for (Piezas pieza : piezasEncontradas) {
            if (pieza.esMovimientoValido(movimiento)) {
                int filaActual = pieza.getFila();
                int columnaActual = pieza.getColumna();
                
                if(movimiento.esCaptura()){
                    posiciones[filaDestino][columnaDestino] = null;
                }
                posiciones[filaActual][columnaActual] = null;
                posiciones[filaDestino][columnaDestino] = pieza;
                pieza.setPosicion(filaDestino, columnaDestino);
                                
                return true;
            }
            
        }
        System.out.println("Movimiento inválido para la pieza " + tipoPieza);
        return false;
    }
    
    /**
     * Convirte la posicion de la pieza en formato pgn a coordenadas
     * para posteriormente ser ubicada en la matriz <code>posiciones</code>.
     * @param casilla
     * @return 
     */
    private int[] convertirNotacionACoordenadas(String casilla){
        char letra;
        int fila;
        int columna;
        int columnaOrigen = -1;
        
        if(casilla.length() > 2){
            letra = casilla.charAt(1);
            fila = 8 - Character.getNumericValue(casilla.charAt(2)); 
            
            columnaOrigen = switch(casilla.charAt(0)){
                case 'a' -> 0;
                case 'b' -> 1;
                case 'c' -> 2;
                case 'd' -> 3;
                case 'e' -> 4;
                case 'f' -> 5;
                case 'g' -> 6;
                case 'h' -> 7;
                default -> -1;
            };
        }else{
            letra = casilla.charAt(0);
            fila = 8 - Character.getNumericValue(casilla.charAt(1)); 
        }
        
        columna = switch(letra){
            case 'a' -> 0;
            case 'b' -> 1;
            case 'c' -> 2;
            case 'd' -> 3;
            case 'e' -> 4;
            case 'f' -> 5;
            case 'g' -> 6;
            case 'h' -> 7;
            default -> -1;
                
        };
        
        return new int[] {fila, columna, columnaOrigen};
    }
    
    /**
     * Busca la pieza que se desea mover dentro de la matriz <code>posiciones</code>.
     * @param tipoPieza el tipo de la pieza.
     * @param color el color de la pieza.
     * @param casillaDestino el arrglo con las ubicaciones de la pieza.
     * @return retorna un arrayList con las piezas encontradas.
     */
    private ArrayList<Piezas> buscarPiezaPorTipoYColor(char tipoPieza, String color, int[] casillaDestino) {
        ArrayList<Piezas> piezasEncontradas = new ArrayList<>();
        int columnaDestino;
        if(casillaDestino[2] != -1){
            columnaDestino = casillaDestino[2];
            for (int fila = 0; fila < 8; fila++) {
                for (int columna = 0; columna < 8; columna++) {
                    Piezas pieza = posiciones[fila][columna];
                    if(pieza != null && pieza.getTipo() == tipoPieza && pieza.getColor().equals(color) && pieza.getColumna() == columnaDestino){
                        piezasEncontradas.add(pieza);
                    }
                }
            }
        }else {
            for (int fila = 0; fila < 8; fila++) {
                for (int columna = 0; columna < 8; columna++) {
                    Piezas pieza = posiciones[fila][columna];
                    if(pieza != null && pieza.getTipo() == tipoPieza && pieza.getColor().equals(color)){
                        piezasEncontradas.add(pieza);
                    }
                }
            }
        }

        return piezasEncontradas;
    }
    
    /**
     * Vacia los arrayList y la matriz con las posiciones para luego llenar la matriz <code>posiciones</code> 
     * con las posiciones por defecto para iniciar de nuevo con la reproduccion de la partida.
     */
    public void reiniciarPartida(){
        historialPosiciones.clear();
        borrarPosiciones();
        inicializarTablero();
        movimientosRevertidos.clear();
        esTurnoBlancas = true;
        cambiarEstado = false;
    }
    
    /**
     * Elimina todas las piezas de la matiz <code>posiciones</code>.
     */
    private void borrarPosiciones(){
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                posiciones[i][j] = null;
            }
        }
    }
    
    /**
     * Obtiene la matiz <code>posiciones</code>.
     * @return la matriz posiciones.
     */
    public Piezas[][] getPosiciones(){
         return posiciones;
    }
     
    /**
     * verifica si una posicion en la matriz <code>posiciones</code> esta ocupada.
     * @param fila la fila de la posicion a buscar.
     * @param columna la columna de la posicion a buscar.
     * @return true si esta ocupada y false si no esta ocupada.
     */
    public boolean estaOcupada(int fila, int columna){
        return posiciones[fila][columna] != null;
    } 
     
    /**
     * Establece el contenido del arrayList <code>movientosPgn</code> con lso movientos en formato pgn.
     * @param movimientos arrayList con los movimientos en formato pgn.
     */
    public void setMovimientosPgn(ArrayList<String> movimientos){
        if(movimientosPgn.isEmpty()){
            this.movimientosPgn = movimientos;
        }else{
            movimientosPgn.clear();
            this.movimientosPgn = movimientos;
        }
    }
    
    /**
     * Carga o guarda una copia de la matriz <code>posiciones</code> en la pila <code>historialPosiciones</code>.
     * @param matrizOriginal matriz la cual se desea copiar o clonar.
     */
    public void cargarNuevaMatriz(Piezas[][] matrizOriginal){
        Piezas[][] matrizClonada = new Piezas[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                matrizClonada[i][j] = matrizOriginal[i][j]; // Copiar los valores
            }
        }
        historialPosiciones.push(matrizClonada);
    }
    
    /**
     * Extrae una matriz del arraylist <code>historialPosiciones</code> para retoceder un moviento.
     * @return la ultima matriz cargada.
     */
    public Piezas[][] extraerMatriz(){
        if(cambiarEstado){
            Piezas[][] quitarTope = historialPosiciones.pop();
            movimientosRevertidos.push(quitarTope);
        }
        
        Piezas[][] matrizAExtraer = historialPosiciones.pop();
        movimientosRevertidos.push(matrizAExtraer);
        cambiarEstado = false;
        return matrizAExtraer;
        
    }
}