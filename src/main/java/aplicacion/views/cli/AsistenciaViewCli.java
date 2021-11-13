package aplicacion.views.cli;

import aplicacion.models.Alumno;

import java.util.Map;

/**
 * Clase que controla los aspectos visuales de la Asistencia, en la interfaz de consola de comandos.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 3.0
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
        if (alumnos.isEmpty()){
            System.out.println("No hay alumnos que cumplan los parametros, vuelva a intentar con otros");
        }else {
            for (Alumno alumno : alumnos.values()) {
                data[i][0] = alumno.getRut();
                data[i][1] = alumno.getApPaterno();
                data[i][2] = alumno.getApMaterno();
                data[i][3] = alumno.getNombres();
                data[i][4] = alumno.getApoderado().getNombreCompleto();
                data[i][5] = alumno.getApoderado().getTelefono();
                if (curso.equals("porcentaje dado ASISTENCIA"))
                    data[i][6] = String.format("%.2f", alumno.getPromAsistencia() * 100);
                else
                    data[i][6] = String.format("%.2f", alumno.promRetiros() * 100);
                i++;
            }
            if (curso.equals("porcentaje dado ASISTENCIA"))
                UtilsCLI.imprimirTabla(data, UtilsCLI.headers.get("asistencia"), "Asistencia");
            else
                UtilsCLI.imprimirTabla(data, UtilsCLI.headers.get("retiros"), "Retiros");

        }
    }
}
