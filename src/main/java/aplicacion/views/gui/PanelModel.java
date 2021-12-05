package aplicacion.views.gui;

import aplicacion.controllers.gui.CustomColors;

import javax.swing.*;
import java.awt.*;

/**
 * Clase que controla la interacción con los cursos en la ejecución de la interfaz gráfica.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 3.0
 */
public abstract class PanelModel extends JPanel {

    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel subtitleLbl;
    private javax.swing.JLabel titleLbl;

    public PanelModel(String title) {
        initComponents();
        this.subtitleLbl.setText(title);
    }

    public PanelModel(String title, String subtitle) {
        initComponents();
        this.subtitleLbl.setText(subtitle);
    }

    public void setSubtitle(String subtitleLbl) {
        this.subtitleLbl.setText(subtitleLbl);
    }

    public void setTitle(String titleLbl) {
        this.titleLbl.setText(titleLbl);
    }

    private JPanel setPanel() {
        return this;
    }

    protected Font getTypography(int size, int type) {
        return new Font("Fira Sans", type, size);
    }

    /**
     * Método que inicializa los componentes
     */
    protected void initComponents() {
        titleLbl = new javax.swing.JLabel();
        subtitleLbl = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        setBackground(CustomColors.LIGHT_BG.getColor());
        setForeground(new java.awt.Color(35, 33, 35));
        setMaximumSize(new java.awt.Dimension(875, 575));
        setMinimumSize(new java.awt.Dimension(875, 575));
        setPreferredSize(new java.awt.Dimension(875, 575));
        setSize(new java.awt.Dimension(875, 575));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titleLbl.setFont(new java.awt.Font("Fira Sans", 1, 48)); // NOI18N
        titleLbl.setForeground(CustomColors.MAIN_COLOR.getColor());
        titleLbl.setText("Bienvenido");
        add(titleLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 450, 0));

        subtitleLbl.setFont(new java.awt.Font("Fira Sans", 0, 24)); // NOI18N
        subtitleLbl.setForeground(CustomColors.MAIN_COLOR.getColor());
        subtitleLbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        subtitleLbl.setText("Subtítulo");
        add(subtitleLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 40, 350, 0));

        jPanel1.setBackground(null);
        jPanel1.setForeground(null);
        jPanel1.setMaximumSize(new java.awt.Dimension(875, 480));
        jPanel1.setMinimumSize(new java.awt.Dimension(875, 480));
        jPanel1.setPreferredSize(new java.awt.Dimension(875, 480));
        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 95, 0, 480));
    }

    ;

}
