package aplicacion.controllers.gui;

import aplicacion.data.datafile.Datafile;
import aplicacion.models.Alumno;
import aplicacion.models.Curso;
import aplicacion.views.cli.UtilsCLI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Clase que genera reportes
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 4.0
 */
public class ReportesControllerGUI {

    /**
     * Método que genera reportes del tipo indicado
     *
     * @param tipo Tipo de reporte a generar
     * @param id Id que identifica sobre cual curso se genera el reporte
     * @return Retorna un tipo Datafile con el reporte del tipo especificado
     */
    private static Datafile generarReporte(String tipo, String id) {
        return new Datafile("reportes/" + tipo + "-" + id);
    }

    /**
     * Metodo que genera reporte de una lista del curso y lo guarda en un archivo de formato CSV
     *
     * @param curso Curso del que se desea generar el reporte
     * @return Retorna la ubicación del reporte
     */
    public static String generarReporteListaCurso(Curso curso) {
        Datafile reporteListaCurso = generarReporte("lista-curso", (curso.getNivel() + "-" + curso.getParalelo()));
        reporteListaCurso.insertLine(Datafile.listToCSV(Arrays.asList(UtilsCLI.headers.get("alumnos"))));
        Map<String, Alumno> alumnos = curso.getAlumnos();
        List<String> line = new ArrayList<>();
        for (Alumno alumno : alumnos.values()) {
            line.add(alumno.getRut());
            line.add(alumno.getApPaterno());
            line.add(alumno.getApMaterno());
            line.add(alumno.getNombres());
            line.add(alumno.getApoderado().getNombreCompleto());
            line.add(Integer.toString(alumno.getApoderado().getTelefono()));
            reporteListaCurso.insertLine(Datafile.listToCSV(line));
            line.clear();
        }
        return reporteListaCurso.getFilePath();
    }

}
