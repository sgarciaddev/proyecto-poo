package aplicacion.views.cli;

import aplicacion.models.Alumno;

import java.util.Map;

/**
 * Clase que controla los aspectos visuales de los Alumnos, en la interfaz de consola de comandos.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 4.0
 */
public class AlumnoViewCLI {

    /**
     * Muestra la tabla con alumnos por pantalla
     *
     * @param alumnos HashMap de Alumnos (RUT) a mostrar
     * @param curso   Texto representativo del curso, para ser mostrado.
     */
    public static void mostrarTablaAlumnos(Map<String, Alumno> alumnos, String curso) {
        int i = 0;
        Object[][] data = new Object[alumnos.size()][UtilsCLI.headers.get("cursos").length];
        for (Alumno alumno : alumnos.values()) {
            data[i][0] = alumno.getRut();
            data[i][1] = alumno.getApPaterno();
            data[i][2] = alumno.getApMaterno();
            data[i][3] = alumno.getNombres();
            data[i][4] = alumno.getApoderado().getNombreCompleto();
            data[i][5] = alumno.getApoderado().getTelefono();
            i++;
        }
        UtilsCLI.imprimirTabla(data, UtilsCLI.headers.get("alumnos"), "Alumnos del " + curso);
    }

}
