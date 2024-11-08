package tablerodeajedrez.modelo;

import java.util.ArrayList;
import java.util.Stack;

public class Tablero {
    private Piezas[][] posiciones;
    private ArrayList<String> movimientosPgn;
    private Stack<Piezas[][]> historialPosiciones;
    private Stack<Piezas[][]> movimientosRevertidos;
    private boolean esTurnoBlancas;
    private boolean cambioEstado;

    public Tablero() {
        this.posiciones = new Piezas[8][8];
        this.movimientosPgn = new ArrayList<>();
        this.historialPosiciones = new Stack<>();
        this.movimientosRevertidos = new Stack<>();
        this.esTurnoBlancas = true;
        this.cambioEstado = false;
        
        inicializarTablero();
    }

    private void inicializarTablero() {
        posiciones[0][0] = new Torre('R', "negra", "/recursos/imagenes/torreNegro.png", 0, 0, this);
        posiciones[0][1] = new Caballo('N', "negra", "/recursos/imagenes/caballoNegro.png", 0, 1);
        posiciones[0][2] = new Alfil('B', "negra", "/recursos/imagenes/alfilNegro.png", 0, 2);
        posiciones[0][3] = new Reina('Q', "negra", "/recursos/imagenes/reinaNegro.png", 0, 3);
        posiciones[0][4] = new Rey('K', "negra", "/recursos/imagenes/reyNegro.png", 0, 4);
        posiciones[0][5] = new Alfil('B', "negra", "/recursos/imagenes/alfilNegro.png", 0, 5);
        posiciones[0][6] = new Caballo('N', "negra", "/recursos/imagenes/caballoNegro.png", 0, 6);
        posiciones[0][7] = new Torre('R', "negra", "/recursos/imagenes/torreNegro.png", 0, 7, this);

        for (int i = 0; i < 8; i++) {
            posiciones[1][i] = new Peon('P', "negra", "/recursos/imagenes/peonNegro.png", 1, i);
        }

        posiciones[7][0] = new Torre('R', "blanca", "/recursos/imagenes/torreBlanco.png", 7, 0, this);
        posiciones[7][1] = new Caballo('N', "blanca", "/recursos/imagenes/caballoBlanco.png", 7, 1);
        posiciones[7][2] = new Alfil('B', "blanca", "/recursos/imagenes/alfilBlanco.png", 7, 2);
        posiciones[7][3] = new Reina('Q', "blanca", "/recursos/imagenes/reinaBlanco.png", 7, 3);
        posiciones[7][4] = new Rey('K', "blanca", "/recursos/imagenes/reyBlanco.png", 7, 4);
        posiciones[7][5] = new Alfil('B', "blanca", "/recursos/imagenes/alfilBlanco.png", 7, 5);
        posiciones[7][6] = new Caballo('N', "blanca", "/recursos/imagenes/caballoBlanco.png", 7, 6);
        posiciones[7][7] = new Torre('R', "blanca", "/recursos/imagenes/torreBlanco.png", 7, 7, this);

        for (int i = 0; i < 8; i++) {
            posiciones[6][i] = new Peon('P', "blanca", "/recursos/imagenes/peonBlanco.png", 6, i);
        }
        
        cargarNuevaMatriz(posiciones);
    }
    
    public Piezas[][] reproducirSiguienteMovimiento(){
        if(!movimientosRevertidos.isEmpty()){
            cambioEstado = true;
            Piezas[][] matriz = movimientosRevertidos.pop();
            cargarNuevaMatriz(matriz);
            return matriz;
        }
        
        String notacion = obtenerMovimientoPgn();
        Movimiento movimiento = analizarMovimiento(notacion);
        cambioEstado = true;
        
        if(moverPieza(movimiento)){
            esTurnoBlancas = !esTurnoBlancas;
            cargarNuevaMatriz(posiciones);
            
            return posiciones;
        }
        return null;
    }
    
    private String obtenerMovimientoPgn(){
        String movimiento = movimientosPgn.get(0);
        movimientosPgn.remove(0);
        
        return movimiento;
    }
    
    private Movimiento analizarMovimiento(String notacion) {
        char tipo = 'P'; 
        int[] casillaDestino = new int[3];
        boolean captura = false;
        boolean enroqueCortoExitoso;
        boolean enroqueLargoExitoso;
        
        // Verifica si es un enroque
        if (notacion.equals("O-O")){
            enroqueCortoExitoso = ejecutarEnroqueCorto();
            
            if(enroqueCortoExitoso && esTurnoBlancas){
                casillaDestino = new int[]{7, 6, -1};
                return new Movimiento('K', casillaDestino, captura);
            }else if(enroqueCortoExitoso && !esTurnoBlancas){
                casillaDestino = new int[]{0, 6, -1};
                return new Movimiento('K', casillaDestino, captura);
            }
            
        } else if(notacion.equals("O-O-O")){
            enroqueLargoExitoso = ejecutarEnroqueLargo();
            
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
            captura = true;
            notacion = notacion.replace("x", ""); // Quita la 'x'
        }

        // Limpia notación de jaque o jaque mate
        notacion = notacion.replace("+", "").replace("#", "");
        casillaDestino = convertirNotacionACoordenadas(notacion);

        return new Movimiento(tipo, casillaDestino, captura);
    }
    
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
    
     public Piezas[][] getPosiciones(){
         return posiciones;
     }
     
    public boolean estaOcupada(int fila, int columna){
        return posiciones[fila][columna] != null;
    } 
     
    
    public void setMovimientos(ArrayList<String> movimientos){
        this.movimientosPgn = movimientos;
    }
    
    public void cargarNuevaMatriz(Piezas[][] matrizOriginal){
        Piezas[][] matrizClonada = new Piezas[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                matrizClonada[i][j] = matrizOriginal[i][j]; // Copiar los valores
            }
        }
        historialPosiciones.push(matrizClonada);
    }
    
    public Piezas[][] extraerMatriz(){
        if(cambioEstado){
            Piezas[][] quitarTope = historialPosiciones.pop();
            movimientosRevertidos.push(quitarTope);
        }
        
        Piezas[][] matrizAExtraer = historialPosiciones.pop();
        movimientosRevertidos.push(matrizAExtraer);
        cambioEstado = false;
        return matrizAExtraer;
        
    }
}

