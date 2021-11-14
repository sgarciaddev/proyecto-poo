/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package aplicacion.views.gui;

import aplicacion.controllers.gui.*;
import aplicacion.data.AlumnoData;
import aplicacion.data.ApoderadoData;
import aplicacion.data.CursoData;
import aplicacion.data.ProfesorData;
import aplicacion.data.database.*;
import aplicacion.data.datafile.AlumnoDF;
import aplicacion.data.datafile.ApoderadoDF;
import aplicacion.data.datafile.CursoDF;
import aplicacion.data.datafile.ProfesorDF;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import javax.swing.Icon;
import javax.swing.UIManager;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;

/**
 * Clase que controla la ejecución de la interfaz gráfica.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 3.0
 */
public class GUI extends javax.swing.JFrame {
    
    private final AlumnoData alumnoData;
    private final ApoderadoData apoderadoData;
    private final ProfesorData profesorData;
    private final CursoData cursoData;
    

    /**
     * Genera la interfaz gráfica.
     */
    public GUI() {
        initComponents();
        this.setLocationRelativeTo(null);
        

        if (DBConnection.connect() != null) {
            MessageUtilsGUI.infoMsg("La conexión con la base de datos fue exitosa.");
            this.alumnoData = new AlumnoDB();
            this.apoderadoData = new ApoderadoDB();
            this.profesorData = new ProfesorDB();
            this.cursoData = new CursoDB();
        } else {
            MessageUtilsGUI.errorMsg("La conexión con la base de datos no pudo realizarse. Se utilizarán los datos locales.");
            this.alumnoData = new AlumnoDF();
            this.apoderadoData = new ApoderadoDF();
            this.profesorData = new ProfesorDF();
            this.cursoData = new CursoDF();
        }
        String[] meses = {"enero","febrero","marzo","abril","mayo","junio","julio","agosto","septiembre"
                ,"octubre","noviembre","diciemrbre"};
        fecha.setText(String.format("Hoy es %d de %s de %d", LocalDate.now().getDayOfMonth(),
                meses[LocalDate.now().getMonthValue() - 1], LocalDate.now().getYear()));
        
        InicioViewGUI p3 = new InicioViewGUI();
        p3.setSize(700, 480);
        p3.setLocation(0,0);
        
        background.removeAll();
        background.add(p3, BorderLayout.CENTER);
        background.revalidate();
        background.repaint();
    }
    
    private Icon calIcon() {
        return IconFontSwing.buildIcon(FontAwesome.CALENDAR, 20, new Color(0, 142, 153));
    }
    
    private Icon plusIcon() {
        return IconFontSwing.buildIcon(FontAwesome.PLUS_CIRCLE, 25, new Color(250, 250, 250));
    }
    
    private Icon listIcon() {
        return IconFontSwing.buildIcon(FontAwesome.LIST, 25, new Color(250, 250, 250));
    }
    
    private Icon searchIcon() {
        return IconFontSwing.buildIcon(FontAwesome.SEARCH, 25, new Color(250, 250, 250));
    }
    
    private Icon exitIcon() {
        return IconFontSwing.buildIcon(FontAwesome.SIGN_OUT, 25, new Color(250, 250, 250));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btAgregar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btMostrarLista = new javax.swing.JButton();
        btBuscar = new javax.swing.JButton();
        background = new javax.swing.JPanel();
        btSalir = new javax.swing.JButton();
        fecha = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(237, 236, 237));
        setForeground(new java.awt.Color(0, 0, 0));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(215, 217, 215));
        jPanel1.setForeground(new java.awt.Color(24, 21, 27));

        jLabel1.setFont(new java.awt.Font("Fira Sans", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 142, 153));
        jLabel1.setText("Sistema de registro de asistencia");

        btAgregar.setBackground(new java.awt.Color(0, 142, 153));
        btAgregar.setFont(new java.awt.Font("Fira Sans", 0, 14)); // NOI18N
        btAgregar.setForeground(new java.awt.Color(250, 250, 250));
        btAgregar.setIcon(plusIcon());
        btAgregar.setText("Agregar alumno");
        btAgregar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 1));
        btAgregar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btAgregar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btAgregar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btAgregar.setIconTextGap(10);
        btAgregar.setLocation(new java.awt.Point(2, 0));
        btAgregar.setMargin(new java.awt.Insets(0, 20, 0, 0));
        btAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAgregarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Fira Sans", 2, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 142, 153));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Menú");

        btMostrarLista.setBackground(new java.awt.Color(0, 142, 153));
        btMostrarLista.setFont(new java.awt.Font("Fira Sans", 0, 14)); // NOI18N
        btMostrarLista.setForeground(new java.awt.Color(250, 250, 250));
        btMostrarLista.setIcon(listIcon());
        btMostrarLista.setText("Mostrar lista");
        btMostrarLista.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 1));
        btMostrarLista.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btMostrarLista.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btMostrarLista.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btMostrarLista.setIconTextGap(10);
        btMostrarLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMostrarListaActionPerformed(evt);
            }
        });

        btBuscar.setBackground(new java.awt.Color(0, 142, 153));
        btBuscar.setFont(new java.awt.Font("Fira Sans", 0, 14)); // NOI18N
        btBuscar.setForeground(new java.awt.Color(250, 250, 250));
        btBuscar.setIcon(searchIcon());
        btBuscar.setText("Buscar");
        btBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 1));
        btBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btBuscar.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btBuscar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btBuscar.setIconTextGap(10);
        btBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBuscarActionPerformed(evt);
            }
        });

        background.setBackground(new java.awt.Color(240, 239, 240));

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 718, Short.MAX_VALUE)
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 461, Short.MAX_VALUE)
        );

        btSalir.setBackground(new java.awt.Color(165, 85, 85));
        btSalir.setFont(new java.awt.Font("Fira Sans", 1, 16)); // NOI18N
        btSalir.setForeground(new java.awt.Color(250, 250, 250));
        btSalir.setIcon(exitIcon());
        btSalir.setText(null);
        btSalir.setBorder(null);
        btSalir.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btSalir.setIconTextGap(6);
        btSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btSalirMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btSalirMouseExited(evt);
            }
        });
        btSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalirActionPerformed(evt);
            }
        });

        fecha.setFont(new java.awt.Font("Fira Sans", 2, 16)); // NOI18N
        fecha.setForeground(new java.awt.Color(0, 142, 153));
        fecha.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        fecha.setIcon(calIcon());
        fecha.setText("Hoy es Sábado 28 de Abril de 2018");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(btSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(8, Short.MAX_VALUE)
                        .addComponent(btAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btMostrarLista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(background, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btMostrarLista, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(background, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(12, Short.MAX_VALUE))))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Permite agregar un alumno al sistema
     *
     * @param evt Evento
     */
    private void btAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAgregarActionPerformed
        // TODO add your handling code here:
        AlumnoViewGUI p1 = new AlumnoViewGUI(this.alumnoData, this.apoderadoData, this.cursoData);
        p1.setSize(700, 480);
        p1.setLocation(0, 0);

        background.removeAll();
        background.add(p1, BorderLayout.CENTER);
        background.revalidate();
        background.repaint();
    }//GEN-LAST:event_btAgregarActionPerformed

    /**
     * Permite mostrar la lista de un curso determinado.
     *
     * @param evt Evento.
     */
    private void btMostrarListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMostrarListaActionPerformed
        // TODO add your handling code here:
       MostrarViewGUI p2 = new MostrarViewGUI(this.cursoData);
       p2.setSize(700, 480);
       p2.setLocation(0, 0);

       background.removeAll();
       background.add(p2, BorderLayout.CENTER);
       background.revalidate();
       background.repaint(); 
    }//GEN-LAST:event_btMostrarListaActionPerformed

    /**
     * Permite salir de la aplicación
     *
     * @param evt Evento
     */
    private void btSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalirActionPerformed
        // TODO add your handling code here:
        int opt = MessageUtilsGUI.msjSiNo("¿Deseas salir de la aplicación?");
        if (opt == 0) System.exit(0);
    }//GEN-LAST:event_btSalirActionPerformed

    /**
     * Permite buscar una persona en el sistema.
     *
     * @param evt Evento.
     */
    private void btBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBuscarActionPerformed
        // TODO add your handling code here:
       BuscarViewGUI p2 = new BuscarViewGUI(this.alumnoData, this.apoderadoData, this.profesorData);
       p2.setSize(700, 480);
       p2.setLocation(0, 0);

       background.removeAll();
       background.add(p2, BorderLayout.CENTER);
       background.revalidate();
       background.repaint();
    }//GEN-LAST:event_btBuscarActionPerformed

    private void btSalirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btSalirMouseEntered
        // TODO add your handling code here:
        btSalir.setText("Salir");
    }//GEN-LAST:event_btSalirMouseEntered

    private void btSalirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btSalirMouseExited
        // TODO add your handling code here:
        btSalir.setText(null);
    }//GEN-LAST:event_btSalirMouseExited

    /**
     * Método main. Ejecuta la interfaz gráfica.
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        
        try {
            GraphicsEnvironment ge = 
                GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("fonts/FiraSans.ttf")));
       } catch (IOException|FontFormatException e) {
            //Handle exception
            e.printStackTrace();
       }
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        IconFontSwing.register(FontAwesome.getIconFont());
        UIManager UI=new UIManager();
        UI.put("OptionPane.background", new Color(235, 237, 235));
        UI.put("Panel.background", new Color(235, 237, 235));
        UI.getLookAndFeelDefaults().put("Panel.background", new Color(235, 237, 235));

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JButton btAgregar;
    private javax.swing.JButton btBuscar;
    private javax.swing.JButton btMostrarLista;
    private javax.swing.JButton btSalir;
    private javax.swing.JLabel fecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}