package aplicacion.controllers.gui;

import java.awt.Color;
import javax.swing.*;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;

/**
 * Clase que controla la generación de mensajes al usuario, en la interfaz gráfica.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 3.0
 */
public class MessageUtilsGUI {
    
    private static final Icon errorIcon = IconFontSwing.buildIcon(FontAwesome.EXCLAMATION_CIRCLE, 40, new Color(255,
            85, 85));
    private static final Icon infoIcon = IconFontSwing.buildIcon(FontAwesome.INFO_CIRCLE, 40, new Color(87, 199, 255));
    private static final Icon questionIcon = IconFontSwing.buildIcon(FontAwesome.QUESTION_CIRCLE, 40, new Color(255, 199, 87));
    
    public MessageUtilsGUI() {
    }

    /**
     *
     *
     * @param mensaje
     */
    public static void errorMsg(String mensaje) {
        JOptionPane.showConfirmDialog(null, mensaje, "Error", JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE , errorIcon);
    }

    /**
     *
     *
     * @param mensaje
     */
    public static void infoMsg(String mensaje) {
        JOptionPane.showConfirmDialog(null, mensaje, "Info", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, infoIcon);
    }

    /**
     * @param mensaje
     * @return
     */
    public static int msjSiNo(String mensaje) {
        return JOptionPane.showConfirmDialog(null, mensaje, "Confirme", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, questionIcon);
    }

}
