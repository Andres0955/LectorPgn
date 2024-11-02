package tablerodeajedrez.modelo;

import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

public class Tablero extends Piezas{
    private HashMap<String, Point> coordenadas;
    private ArrayList<Piezas> piezas;
    
    public Tablero(){
        super(700, 700, 0, 0, "/recursos/imagenes/tableroMarron.png");
        this.coordenadas = new HashMap<>();
        this.piezas = new ArrayList<>();
        cargarCoordenadas();
    }
    
    private void cargarCoordenadas(){
        char[] letras = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        int posX = 45, posY = 55, tamañoCasillaX = 77, tamañoCasillaY = 75;
        
        
        for(int fila = 0; fila < 8; fila++){
            for(int columna = 0; columna < 8; columna++){
                String id = "" + letras[columna] + (8 - fila); 
                if(fila == 0 || fila == 1){
                    posX = columna * tamañoCasillaX + 50;
                    posY = fila * tamañoCasillaY + 60; 
                }else if(fila == 6 || fila == 7){
                    posX = columna * tamañoCasillaX + 50;
                    posY = fila * tamañoCasillaY + 55; 
                }else{
                    posX = columna * tamañoCasillaX;
                    posY = fila * tamañoCasillaY; 
                }
                  
                coordenadas.put(id, new Point(posX, posY));
            }
        }
        cargarPiezas();
    }
    
    private void cargarPiezas() {
    // Cargar torres
    cargarPieza("Torre", "a1", "a8", "h1", "h8");

    // Cargar caballos
    cargarPieza("Caballo", "b1", "b8", "g1", "g8");

    // Cargar alfiles
    cargarPieza("Alfil", "c1", "c8", "f1", "f8");

    // Cargar reinas
    cargarPieza("Reina", "d1", "d8");

    // Cargar reyes
    cargarPieza("Rey", "e1", "e8");

    // Cargar peones
    cargarPeones("2", "7");
}

    private void cargarPieza(String tipo, String... posiciones) {
        for (int i=0; i < posiciones.length; i++) {
            Piezas pieza;
            if(i%2 == 0){
                pieza = crearPieza(tipo, "Blanco");
            }else{
                pieza = crearPieza(tipo, "Negro");
            }
            
            if (pieza != null) {
                pieza.setPosicion(coordenadas.get(posiciones[i]));
                piezas.add(pieza);
            }
        }
    }

    private void cargarPeones(String filaBlanca, String filaNegra) {
        for (char col = 'a'; col <= 'h'; col++) {
            // Peones blancos
            Peon peonBlanco = new Peon("/recursos/imagenes/peonBlanco.png");
            peonBlanco.setPosicion(coordenadas.get(col + filaBlanca));
            piezas.add(peonBlanco);

            // Peones negros
            Peon peonNegro = new Peon("/recursos/imagenes/peonNegro.png");
            peonNegro.setPosicion(coordenadas.get(col + filaNegra));
            piezas.add(peonNegro);
        }
    }

    private Piezas crearPieza(String tipo, String color) {
        switch (tipo) {
            case "Torre": 
                return new Torre("/recursos/imagenes/torre" + color + ".png");
            case "Caballo": 
                return new Caballo("/recursos/imagenes/caballo" + color + ".png");
            case "Alfil": 
                return new Alfil("/recursos/imagenes/alfil" + color + ".png");
            case "Reina": 
                return new Reina("/recursos/imagenes/reina" + color + ".png");
            case "Rey": 
                return new Rey("/recursos/imagenes/rey" + color + ".png");
            default: 
                return null;
        }
    }
    
    public ArrayList<Piezas> getPiezas(){
        return piezas;
    }
}
