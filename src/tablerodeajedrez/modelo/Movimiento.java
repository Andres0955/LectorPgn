package tablerodeajedrez.modelo;

public class Movimiento {
    private char tipo;
    private int[] casillaDestino;
    private boolean captura;
    private boolean enroque;


    public Movimiento(char tipo, int[] casillaDestino, boolean captura) {
        this.tipo = tipo;
        this.casillaDestino = casillaDestino;
        this.captura = captura;
    }


    public char getTipo() {
        return tipo;
    }

    public int getFila() {
        return casillaDestino[0];
    }
    
    public int getColumna() {
        return casillaDestino[1];
    }
    
    public int[] getCasillaDestino(){
        return casillaDestino;
    }

    public boolean esCaptura() {
        return captura;
    }
    
    public boolean esEnroque(){
        return enroque;
    }
}
