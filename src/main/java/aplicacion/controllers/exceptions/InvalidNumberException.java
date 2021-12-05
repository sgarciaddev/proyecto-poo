package aplicacion.controllers.exceptions;

import aplicacion.controllers.gui.UtilsGUI;

public class InvalidNumberException extends NumberFormatException {

    public InvalidNumberException() {
        super("El valor ingresado no corresponde a un número telefónico válido.");
    }

    public void mostrarMensajeError() {
        UtilsGUI.errorMsg("El formato del teléfono debe ser numérico.");
    }

}
