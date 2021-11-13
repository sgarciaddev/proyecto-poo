package aplicacion.views.gui;

import javax.swing.*;

public class MessageGUI {

    public static void errorMsg(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void infoMsg(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    public static int msjSiNo(String mensaje) {
        return JOptionPane.showConfirmDialog(null, mensaje, "Confirme", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    }



}
