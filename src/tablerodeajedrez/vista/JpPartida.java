package tablerodeajedrez.vista;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import tablerodeajedrez.controlador.Control;
import tablerodeajedrez.modelo.*;

public class JpPartida extends javax.swing.JPanel {
    private Control control;
    private static final int TAMANO_CASILLA = 75; 
    private static final int MARGEN = 50;
    private static final int DIMENSION_TABLERO = 8; 
    private Piezas[][] posiciones;
    private Map<String, Point> coordenadas;
    
    public JpPartida(Control control, Tablero tablero) {
        this.control = control;
        this.posiciones = new Piezas[8][8];
        this.coordenadas = new HashMap<>();
        
        initComponents();
        inicializarMapaPosiciones();
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        dibujarTablero(g);
        dibujarPiezas(g);
        
    }
    
    private void inicializarMapaPosiciones() {
        for (int fila = 0; fila < DIMENSION_TABLERO; fila++) {
            for (int columna = 0; columna < DIMENSION_TABLERO; columna++) {
                
                int x = MARGEN + columna * TAMANO_CASILLA + 5;
                int y = MARGEN + fila * TAMANO_CASILLA + 10;

                
                String id = fila +""+ columna;
                coordenadas.put(id, new Point(x, y));
            }
        }
    }

    
    public void dibujarTablero(Graphics g) {
        
        for (int fila = 0; fila < DIMENSION_TABLERO; fila++) {
            for (int col = 0; col < DIMENSION_TABLERO; col++) {
                
                int x = MARGEN + col * TAMANO_CASILLA;
                int y = MARGEN + fila * TAMANO_CASILLA;

                if ((fila + col) % 2 == 0) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.GRAY);
                }
                g.fillRect(x, y, TAMANO_CASILLA, TAMANO_CASILLA);
            }
        }
        
        // Dibujar números en los márgenes izquierdo y derecho
        g.setColor(Color.BLACK);
        for (int fila = 0; fila < DIMENSION_TABLERO; fila++) {
            int y = MARGEN + fila * TAMANO_CASILLA + TAMANO_CASILLA / 2;
            g.drawString(String.valueOf(DIMENSION_TABLERO - fila), MARGEN / 2, y);
            g.drawString(String.valueOf(DIMENSION_TABLERO - fila), MARGEN + DIMENSION_TABLERO * TAMANO_CASILLA + 10, y);
        }

        // Dibujar letras en los márgenes superior e inferior
        for (int columna = 0; columna < DIMENSION_TABLERO; columna++) {
            char letra = (char) ('a' + columna);
            int x = MARGEN + columna * TAMANO_CASILLA + TAMANO_CASILLA / 2;
            g.drawString(String.valueOf(letra), x, MARGEN / 2);
            g.drawString(String.valueOf(letra), x, MARGEN + DIMENSION_TABLERO * TAMANO_CASILLA + 20);
        }

    }
    
    private void dibujarPiezas(Graphics g){
        
        if (posiciones == null) {
        System.err.println("El arreglo de posiciones no ha sido inicializado.");
        return;
    }
        
        for (int fila = 0; fila < DIMENSION_TABLERO; fila++) {
            for (int columna = 0; columna < DIMENSION_TABLERO; columna++) {
                Piezas pieza = posiciones[fila][columna];
            if (pieza != null) {
                 Point coordenadaActual = coordenadas.get(fila+""+columna);
                 int posX = coordenadaActual.x;
                 int posY = coordenadaActual.y;
                 
                g.drawImage(pieza.getImagen(), posX, posY, pieza.getAncho(), pieza.getAlto(), null);
            }
            }
        }
    }
    
    public void actualizarPanel(){
        repaint();
    }
     
    public void setPosiciones(Piezas[][] posiciones){
        this.posiciones = posiciones;
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        btnAvanzar = new javax.swing.JButton();
        btnRetroceder = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("Reproducir");
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        btnAvanzar.setText("Avanzar");
        btnAvanzar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAvanzarActionPerformed(evt);
            }
        });
        jPanel1.add(btnAvanzar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        btnRetroceder.setText("Retroceder");
        btnRetroceder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetrocederActionPerformed(evt);
            }
        });
        jPanel1.add(btnRetroceder, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 117, -1, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 0, 150, 700));
    }// </editor-fold>//GEN-END:initComponents

    private void btnAvanzarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAvanzarActionPerformed
        control.avanzar();
        actualizarPanel();
    }//GEN-LAST:event_btnAvanzarActionPerformed

    private void btnRetrocederActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetrocederActionPerformed
        control.retroceder();
        actualizarPanel();
    }//GEN-LAST:event_btnRetrocederActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAvanzar;
    private javax.swing.JButton btnRetroceder;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
