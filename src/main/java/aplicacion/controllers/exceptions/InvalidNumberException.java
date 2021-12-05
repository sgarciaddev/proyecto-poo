package aplicacion.controllers.exceptions;

import aplicacion.controllers.gui.UtilsGUI;

/**
 * Excepción de ingreso de datos no numéricos
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 4.0
 */
public class InvalidNumberException extends NumberFormatException {

    /**
     * Permite lanzar la excepción de datos inválidos de típo numérico.
     */
    public InvalidNumberException() {
        super("El valor ingresado no corresponde a un número telefónico válido.");
    }

    /**
     * Método que permite mostrar por pantalla mensaje de error.
     */
    public void mostrarMensajeError() {
        UtilsGUI.errorMsg("El formato del teléfono debe ser numérico.");
    }

}
