package lectorPgn.vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import lectorPgn.controlador.Control;
import lectorPgn.modelo.Piezas;

public class JpPartida extends javax.swing.JPanel {
    private Control control;
    private static final int TAMANO_CASILLA = 75; 
    private static final int MARGEN = 50;
    private static final int DIMENSION_TABLERO = 8; 
    private Piezas[][] posiciones;
    private Map<String, Point> coordenadas;
    private Color[] colorTablero;
    private ImageIcon fondo;
    
    public JpPartida(Control control) {
        this.control = control;
        this.posiciones = new Piezas[8][8];
        this.coordenadas = new HashMap<>();
        this.colorTablero = new Color[2];
        this.fondo = new ImageIcon(getClass().getResource("/recursos/imagenes/fondo4.png"));
        
        initComponents();
        inicializarMapaPosiciones();
        elegirColores(0);
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        if(fondo != null){
            g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
        
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
        char[] letras = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        for (int fila = 0; fila < DIMENSION_TABLERO; fila++) {
            for (int col = 0; col < DIMENSION_TABLERO; col++) {
                
                int x = MARGEN + col * TAMANO_CASILLA;
                int y = MARGEN + fila * TAMANO_CASILLA;

                if ((fila + col) % 2 == 0) {
                    g.setColor(colorTablero[0]);
                } else {
                    g.setColor(colorTablero[1]);
                }
                g.fillRect(x, y, TAMANO_CASILLA, TAMANO_CASILLA);
            }
        }
        
        //Numeros en los márgenes izquierdo y derecho
        g.setColor(Color.white);
        g.setFont(new Font("Alergian", Font.ITALIC, 15));
        for (int fila = 0; fila < DIMENSION_TABLERO; fila++) {
            int y = MARGEN + fila * TAMANO_CASILLA + TAMANO_CASILLA / 2;
            g.drawString(String.valueOf(DIMENSION_TABLERO - fila), MARGEN / 2, y);
            g.drawString(String.valueOf(DIMENSION_TABLERO - fila), MARGEN + DIMENSION_TABLERO * TAMANO_CASILLA + 10, y);
        }
        
        // Letras en los márgenes superior e inferior
        for (int columna = 0; columna < DIMENSION_TABLERO; columna++) {
            char letra = letras[columna];
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
    
    private void elegirColores(int eleccion){
        if(eleccion == 0){
            colorTablero[0] = Color.white;
            colorTablero[1] = Color.gray;
        }else{
            colorTablero[0] = new Color(234, 195, 120);
            colorTablero[1] = new Color(55, 32, 24);
        }
    }
    
    public void actualizarInformacion(String informacion){
        txtInformacion.setText(informacion);
    }
     
    public void setPosiciones(Piezas[][] posiciones){
        this.posiciones = posiciones;
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnAvanzar = new javax.swing.JButton();
        btnRetroceder = new javax.swing.JButton();
        btnReiniciar = new javax.swing.JButton();
        btnReproducir = new javax.swing.JToggleButton();
        btnTema = new javax.swing.JToggleButton();
        txtInformacion = new javax.swing.JTextField();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAvanzar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagenes/botones/btnAvanzar.png"))); // NOI18N
        btnAvanzar.setBorderPainted(false);
        btnAvanzar.setContentAreaFilled(false);
        btnAvanzar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAvanzar.setFocusPainted(false);
        btnAvanzar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAvanzarActionPerformed(evt);
            }
        });
        jPanel1.add(btnAvanzar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, 50, 50));

        btnRetroceder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagenes/botones/btnRetroceder.png"))); // NOI18N
        btnRetroceder.setBorderPainted(false);
        btnRetroceder.setContentAreaFilled(false);
        btnRetroceder.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRetroceder.setFocusPainted(false);
        btnRetroceder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetrocederActionPerformed(evt);
            }
        });
        jPanel1.add(btnRetroceder, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 50, 50));

        btnReiniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagenes/botones/btnReiniciar.png"))); // NOI18N
        btnReiniciar.setBorderPainted(false);
        btnReiniciar.setContentAreaFilled(false);
        btnReiniciar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReiniciar.setFocusPainted(false);
        btnReiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReiniciarActionPerformed(evt);
            }
        });
        jPanel1.add(btnReiniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, 50, 50));

        btnReproducir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagenes/botones/btnReproducir.png"))); // NOI18N
        btnReproducir.setBorderPainted(false);
        btnReproducir.setContentAreaFilled(false);
        btnReproducir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReproducir.setFocusPainted(false);
        btnReproducir.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagenes/botones/btnPausar.png"))); // NOI18N
        btnReproducir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReproducirActionPerformed(evt);
            }
        });
        jPanel1.add(btnReproducir, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 50, 50));

        btnTema.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagenes/botones/btnModoClaro.png"))); // NOI18N
        btnTema.setSelected(true);
        btnTema.setBorderPainted(false);
        btnTema.setContentAreaFilled(false);
        btnTema.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTema.setFocusPainted(false);
        btnTema.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/imagenes/botones/btnModoOscuro.png"))); // NOI18N
        btnTema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTemaActionPerformed(evt);
            }
        });
        jPanel1.add(btnTema, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 600, 40, 40));

        txtInformacion.setBackground(new java.awt.Color(0, 0, 0));
        txtInformacion.setFont(new java.awt.Font("Algerian", 0, 12)); // NOI18N
        txtInformacion.setForeground(new java.awt.Color(255, 255, 255));
        txtInformacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtInformacion.setText("Game Details");
        txtInformacion.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtInformacion.setEnabled(false);
        txtInformacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtInformacionActionPerformed(evt);
            }
        });
        jPanel1.add(txtInformacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 280, 140, 150));

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

    private void btnReproducirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReproducirActionPerformed
        if(btnReproducir.isSelected()){
            control.iniciarTemporizador();
        }else{
            control.pararTemporizador();
        }
    }//GEN-LAST:event_btnReproducirActionPerformed

    private void btnReiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReiniciarActionPerformed
        control.reiniciar();
    }//GEN-LAST:event_btnReiniciarActionPerformed

    private void btnTemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTemaActionPerformed
        if(btnTema.isSelected()){
            elegirColores(0);
        }else{
            elegirColores(1);
        }
        
        actualizarPanel();
            
    }//GEN-LAST:event_btnTemaActionPerformed

    private void txtInformacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtInformacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtInformacionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAvanzar;
    private javax.swing.JButton btnReiniciar;
    private javax.swing.JToggleButton btnReproducir;
    private javax.swing.JButton btnRetroceder;
    private javax.swing.JToggleButton btnTema;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtInformacion;
    // End of variables declaration//GEN-END:variables
}
