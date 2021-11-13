package aplicacion.controllers.gui;

import javax.swing.*;

/**
 * Clase que controla la generación de mensajes al usuario, en la interfaz gráfica.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 3.0
 */
public class MessageUtilsGUI {

    /**
     *
     *
     * @param mensaje
     */
    public static void errorMsg(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     *
     *
     * @param mensaje
     */
    public static void infoMsg(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     *
     * @param mensaje
     * @return
     */
    public static int msjSiNo(String mensaje) {
        return JOptionPane.showConfirmDialog(null, mensaje, "Confirme", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    }

}
