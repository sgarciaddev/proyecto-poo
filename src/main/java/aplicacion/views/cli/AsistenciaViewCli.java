package aplicacion.views.cli;


/**
 * Clase que controla los aspectos visuales de la Asistencia, en la interfaz de consola de comandos.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 1.0
 */

public class AsistenciaViewCli {

    /**
     * Despliega el menú de gestión de asistencia por pantalla.
     */
    public void mostrarMenuAsistencia() {
        UtilsCLI.imprimirMenu(UtilsCLI.opcMenuAsistencia, "Menu Asistencia");
    }

}
