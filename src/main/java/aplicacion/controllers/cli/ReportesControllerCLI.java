package aplicacion.controllers.cli;

import aplicacion.models.IDCurso;
import aplicacion.views.cli.MenuCLI;

import java.io.IOException;

public class ReportesControllerCLI {

    private final MenuCLI menuCLI;

    public ReportesControllerCLI( MenuCLI menuCLI) {
        this.menuCLI = menuCLI;
    }

    public void generarListaCurso(CursoControllerCLI cursoController) throws IOException {
        IDCurso idCurso;
        String rutaArchivo;

        idCurso = cursoController.obtenerIDCurso();
        System.out.println("Generar reporte del curso " + idCurso.nivel + "/" + idCurso.paralelo);
        rutaArchivo = cursoController.generarReporteListaCurso(idCurso.nivel, idCurso.paralelo);
        if (!rutaArchivo.equals(""))
            System.out.println("Archivo generado: " + rutaArchivo);
        else
            System.out.println("Ha ocurrido un error, por favor intente nuevamente.");
    }

    public void generarTablaCursos(CursoControllerCLI cursoController) throws IOException {
        String rutaArchivo;

        System.out.println("Generar reporte de todos los cursos");
        rutaArchivo = cursoController.generarReporteTablaCursos();
        if (!rutaArchivo.equals(""))
            System.out.println("Archivo generado: " + rutaArchivo);
        else
            System.out.println("Ha ocurrido un error, por favor intente nuevamente.");
    }

}
