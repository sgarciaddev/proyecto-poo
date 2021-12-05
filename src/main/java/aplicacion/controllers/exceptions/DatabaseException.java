package aplicacion.controllers.exceptions;

import aplicacion.controllers.gui.UtilsGUI;

import java.sql.SQLException;

public class DatabaseException extends SQLException {

    public DatabaseException(String message) {
        super(message);
    }

    public DatabaseException() {
        super("La conexión a la base de datos no pudo realizarse");
    }

    public void mostrarMensajeError() {
        UtilsGUI.errorMsg("La conexión con la base de datos no pudo realizarse. Se utilizarán los datos locales.");
    }

}
