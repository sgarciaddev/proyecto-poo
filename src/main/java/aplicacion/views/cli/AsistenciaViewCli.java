package aplicacion.views.cli;

import aplicacion.models.Alumno;

import java.util.Map;

/**
 * Clase que controla los aspectos visuales de la Asistencia, en la interfaz de consola de comandos.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 2.0
 */

public class AsistenciaViewCli {

    /**
     * Muestra la tabla con asistencia de los alumnos entregados
     *
     * @param alumnos HashMap de Alumnos (RUT) a mostrar
     * @param curso   Texto representativo del curso, para ser mostrado.
     */
    public static void mostrarTablaAlumnosAsistencia(Map<String, Alumno> alumnos, String curso) {
        int i = 0;
        Object[][] data = new Object[alumnos.size()][UtilsCLI.headers.get("asistencia").length];
        for (Alumno alumno : alumnos.values()) {
            data[i][0] = alumno.getRut();
            data[i][1] = alumno.getApPaterno();
            data[i][2] = alumno.getApMaterno();
            data[i][3] = alumno.getNombres();
            data[i][4] = alumno.getApoderado().getNombreCompleto();
            data[i][5] = alumno.getApoderado().getTelefono();
            data[i][6] = alumno.getAsistencia().obtenerAsistencia();
            i++;
        }
        UtilsCLI.imprimirTabla(data, UtilsCLI.headers.get("asistencia"), "Asistencia de alumnos del " + curso);
    }

}
