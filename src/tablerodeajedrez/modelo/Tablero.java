package tablerodeajedrez.modelo;

import java.util.ArrayList;

public class Tablero {
    private Piezas[][] posiciones;
    private ArrayList<String> movimientosPgn;
    private boolean esTurnoBlancas;

    public Tablero() {
        this.posiciones = new Piezas[8][8];
        this.movimientosPgn = new ArrayList<>();
        this.esTurnoBlancas = true;
        
        inicializarTablero();
    }

    private void inicializarTablero() {
        posiciones[0][0] = new Torre("negra", "/recursos/imagenes/torreNegro.png", 0, 0);
        posiciones[0][1] = new Caballo("negra", "/recursos/imagenes/caballoNegro.png", 0, 1);
        posiciones[0][2] = new Alfil("negra", "/recursos/imagenes/alfilNegro.png", 0, 2);
        posiciones[0][3] = new Reina("negra", "/recursos/imagenes/reinaNegro.png", 0, 3);
        posiciones[0][4] = new Rey("negra", "/recursos/imagenes/reyNegro.png", 0, 4);
        posiciones[0][5] = new Alfil("negra", "/recursos/imagenes/alfilNegro.png", 0, 5);
        posiciones[0][6] = new Caballo("negra", "/recursos/imagenes/caballoNegro.png", 0, 6);
        posiciones[0][7] = new Torre("negra", "/recursos/imagenes/torreNegro.png", 0, 7);

        for (int i = 0; i < 8; i++) {
            posiciones[1][i] = new Peon("negra", "/recursos/imagenes/peonNegro.png", 1, i);
        }

        posiciones[7][0] = new Torre("blanca", "/recursos/imagenes/torreBlanco.png", 7, 0);
        posiciones[7][1] = new Caballo("blanca", "/recursos/imagenes/caballoBlanco.png", 7, 1);
        posiciones[7][2] = new Alfil("blanca", "/recursos/imagenes/alfilBlanco.png", 7, 2);
        posiciones[7][3] = new Reina("blanca", "/recursos/imagenes/reinaBlanco.png", 7, 3);
        posiciones[7][4] = new Rey("blanca", "/recursos/imagenes/reyBlanco.png", 7, 4);
        posiciones[7][5] = new Alfil("blanca", "/recursos/imagenes/alfilBlanco.png", 7, 5);
        posiciones[7][6] = new Caballo("blanca", "/recursos/imagenes/caballoBlanco.png", 7, 6);
        posiciones[7][7] = new Torre("blanca", "/recursos/imagenes/torreBlanco.png", 7, 7);

        for (int i = 0; i < 8; i++) {
            posiciones[6][i] = new Peon("blanca", "/recursos/imagenes/peonBlanco.png", 6, i);
        }
    }
    
    public Piezas[][] reproducirSiguienteMovimiento(){
        String notacion = obtenerMovimientoPgn();
        Movimiento movimiento = analizarMovimiento(notacion);
        if(moverPieza(movimiento)){
            esTurnoBlancas = !esTurnoBlancas;
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
        String casillaDestino = "";
        boolean captura = false;

        // Verifica si es un enroque
        if (notacion.equals("O-O")) {
            return new Movimiento('K', "G1"); // Enroque corto, por ejemplo
        } else if (notacion.equals("O-O-O")) {
            return new Movimiento('K', "C1"); // Enroque largo, por ejemplo
        }

        // Determina la pieza en base a la primera letra
        if (notacion.charAt(0) >= 'A' && notacion.charAt(0) <= 'Z') {
            switch (notacion.charAt(0)) {
                case 'K':
                    tipo = 'K';
                    break;
                case 'Q':
                    tipo = 'Q';
                    break;
                case 'R':
                    tipo = 'R';
                    break;
                case 'B':
                    tipo = 'B';
                    break;
                case 'N':
                    tipo = 'N';
                    break;
                default:
                    tipo = 'P';
            }
            casillaDestino = notacion.substring(1); // El resto es la casilla
        } else {
            casillaDestino = notacion; // Directamente si es un peón
        }

        // Verifica si hay captura en el movimiento
        if (notacion.contains("x")) {
            captura = true;
            casillaDestino = casillaDestino.replace("x", ""); // Quita la 'x'
        }

        // Limpia notación de jaque o jaque mate
        casillaDestino = casillaDestino.replace("+", "").replace("#", "");

        return new Movimiento(tipo, casillaDestino, captura);
    }

    private boolean moverPieza(Movimiento movimiento) {
        char tipoPieza = movimiento.getTipo();
        String casillaDestino = movimiento.getCasillaDestino();
        
        int[] coordenadasDestino = convertirNotacionACoordenadas(casillaDestino);
        int filaDestino = coordenadasDestino[0];
        int columnaDestino = coordenadasDestino[1];
        String color = esTurnoBlancas ? "blanca" : "negra";
        ArrayList<Piezas> piezasEncontradas;

        piezasEncontradas = buscarPiezaPorTipoYColor(tipoPieza, color);

        for (Piezas pieza : piezasEncontradas) {
            if (pieza.esMovimientoValido(filaDestino, columnaDestino)) {
                int filaActual = pieza.getFila();
                int columnaActual = pieza.getColumna();

                posiciones[filaActual][columnaActual] = null;
                posiciones[filaDestino][columnaDestino] = pieza;
                pieza.setPosicion(filaDestino, columnaDestino);

                return true;
            }
        }
        System.out.println("Movimiento inválido para la pieza " + tipoPieza);
        return false;
    }
    
    private int[] convertirNotacionACoordenadas(String casilla) {
        int columna = casilla.charAt(0) - 'a'; 
        int fila = 8 - Character.getNumericValue(casilla.charAt(1)); 
        return new int[] {fila, columna};
    }
    
    private ArrayList<Piezas> buscarPiezaPorTipoYColor(char tipoPieza, String color) {
        ArrayList<Piezas> piezasEncontradas = new ArrayList<>();
        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {
                Piezas pieza = posiciones[fila][columna];
                if(pieza != null && pieza.getTipo() == tipoPieza && pieza.getColor().equals(color)){
                    piezasEncontradas.add(pieza);
                }
            }
        }
        return piezasEncontradas;
    }
    
     public Piezas[][] getPosiciones(){
         return posiciones;
     }
    
    public void setMovimientos(ArrayList<String> movimientos){
        this.movimientosPgn = movimientos;
    }
}

