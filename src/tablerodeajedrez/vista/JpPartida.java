package tablerodeajedrez.vista;

import java.awt.Graphics;
import java.util.ArrayList;
import tablerodeajedrez.controlador.Control;
import tablerodeajedrez.modelo.*;

public class JpPartida extends javax.swing.JPanel {
    private Tablero tablero;
    private ArrayList<Piezas> piezas;
    
    public JpPartida(Control control, Tablero tablero) {
        this.tablero = tablero;
        this.piezas = tablero.getPiezas();
        initComponents();
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        g.drawImage(tablero.getImagen(), tablero.getPosX(), tablero.getPosY(), tablero.getAncho(), tablero.getAlto(), null);
        for(Piezas pieza: piezas){
            g.drawImage(pieza.getImagen(), pieza.getPosX(), pieza.getPosY(), pieza.getAncho(), pieza.getAlto(), null);
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("Reproducir");
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jButton2.setText("Avanzar");
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        jButton3.setText("Retroceder");
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 117, -1, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 0, 150, 700));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
