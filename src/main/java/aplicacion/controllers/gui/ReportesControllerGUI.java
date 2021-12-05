package aplicacion.controllers.gui;

import aplicacion.data.datafile.Datafile;
import aplicacion.models.Alumno;
import aplicacion.models.Curso;
import aplicacion.views.cli.UtilsCLI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ReportesControllerGUI {

    private static Datafile generarReporte(String tipo, String id) {
        return new Datafile("reportes/" + tipo + "-" + id);
    }

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
