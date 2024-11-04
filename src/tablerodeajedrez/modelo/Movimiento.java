package tablerodeajedrez.modelo;

public class Movimiento {
    private char tipo;
    private String casillaDestino;
    private boolean captura;

    public Movimiento(char tipo, String casillaDestino) {
        this.tipo = tipo;
        this.casillaDestino = casillaDestino;
        this.captura = false;
    }

    public Movimiento(char tipo, String casillaDestino, boolean captura) {
        this.tipo = tipo;
        this.casillaDestino = casillaDestino;
        this.captura = captura;
    }

    public char getTipo() {
        return tipo;
    }

    public String getCasillaDestino() {
        return casillaDestino;
    }

    public boolean isCaptura() {
        return captura;
    }
}
