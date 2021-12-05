package aplicacion.controllers.gui;

import aplicacion.models.Curso;
import jiconfont.IconCode;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Clase que controla la generación de mensajes al usuario, en la interfaz gráfica.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 3.0
 */
public class UtilsGUI {
//    private static final Icon UtilsGUI.getIcon(FontAwesome.QUESTION_CIRCLE, 40, Colors.QUESTION_YELLOW.getColor()) = IconFontSwing.buildIcon(FontAwesome.QUESTION_CIRCLE, 40, );

    public UtilsGUI() {
    }
  
    // Todo: Generar documentacion
    /**
     *
     * @param cursoData
     * @return
     */
    public static DefaultComboBoxModel getCursosCB(List<Curso> cursoData) {
        String labels[] = new String[cursoData.size()];
        int i = 0;
        for (Curso curso : cursoData) {
            labels[i] = curso.cursoToString();
            i++;
        }
        return new DefaultComboBoxModel(labels);
    }

    // Todo: Generar documentacion
    /**
     *
     *
     * @param comboBox
     * @param cursoData
     * @return
     */
    public static Curso getCursoFromCB(JComboBox comboBox, List<Curso> cursoData) {
        Curso curso = null;
        for (Curso c : cursoData) {
            if (c != null && c.cursoToString().equals(comboBox.getSelectedItem().toString())) {
                curso = c;
            }
        }
        return curso;
    }

    /**
     * Método para getter para obtener icono
     *
     * @param icon Codigo del icono a obtener
     * @param size Tamaño del que tendrá el icono
     * @param color Color que tendrá el icono
     * @return Retorna el objeto icono con los parámetros indicados
     */
    public static Icon getIcon(IconCode icon, float size, Color color) {
        return IconFontSwing.buildIcon(icon, size, color);
    }

    /**
     * Método para obtener la fecha al dia de la consulta
     *
     * @return Retorna un string con la fecha actual
     */
    public static String getActualDate() {
        String[] meses = {"enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre"
                , "octubre", "noviembre", "diciemrbre"};
        return String.format("Hoy es %d de %s de %d", LocalDate.now().getDayOfMonth(),
                meses[LocalDate.now().getMonthValue() - 1], LocalDate.now().getYear());
    }

    /**
     * @param mensaje
     * Método para generar un mensaje de error y botón de confirmación
     *
     * @param mensaje Mensaje de error para el usuario
     */
    public static void errorMsg(String mensaje) {
        JOptionPane.showConfirmDialog(null,
                mensaje,
                "Error",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.ERROR_MESSAGE,
                UtilsGUI.getIcon(FontAwesome.EXCLAMATION_CIRCLE, 40, CustomColors.DANGER_RED.getColor()));
    }

    /**
     *
     *
     * @param mensaje
     * Método para generar un mensaje con información con botón de confirmación
     *
     * @param mensaje Mensaje con la información para el usuario
     */
    public static void infoMsg(String mensaje) {
        JOptionPane.showConfirmDialog(null, mensaje, "Info", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, UtilsGUI.getIcon(FontAwesome.INFO_CIRCLE, 40, CustomColors.INFO_BLUE.getColor()));
    }

    /**
     * @param mensaje
     * @return
     * Método para generar un mensaje para confirmar opción y botón de confirmación
     *
     * @param mensaje Mensaje que contiene la información a confirmar.
     * @return Retorna la opción seleccionada por el usuario.
     */
    public static int msjSiNo(String mensaje) {
        return JOptionPane.showConfirmDialog(null, mensaje, "Confirme", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, UtilsGUI.getIcon(FontAwesome.QUESTION_CIRCLE, 40, CustomColors.QUESTION_YELLOW.getColor()));
    }

}