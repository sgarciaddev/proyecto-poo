package aplicacion.controllers.cli;

import aplicacion.models.IDCurso;
import aplicacion.views.cli.MenuCLI;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Clase controladora de los reportes que genera el sistema.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 3.0
 */
public class ReportesControllerCLI {

    private final MenuCLI menuCLI;

    /**
     * Genera un objeto de tipo ReporteControllerCLI
     *
     * @param menuCLI MenuCLI para obtener acceso a los datos.
     */
    public ReportesControllerCLI( MenuCLI menuCLI) {
        this.menuCLI = menuCLI;
    }

    /**
     * Genera el reporte con la lista de un curso específico.
     *
     * @param cursoController Controlador de cursos
     * @throws IOException Errores de entrada/salida de datos.
     */
    public void generarListaCurso(CursoControllerCLI cursoController) throws IOException {
        IDCurso idCurso;
        String rutaArchivo;

        idCurso = cursoController.obtenerIDCurso();
        System.out.println("Generar reporte del curso " + idCurso.nivel + "/" + idCurso.paralelo);
        rutaArchivo = cursoController.generarReporteListaCurso(idCurso.nivel, idCurso.paralelo);
        if (!rutaArchivo.equals("")) {
            System.out.println("Archivo generado: " + rutaArchivo);
            Desktop.getDesktop().open(new File(rutaArchivo));
        } else
            System.out.println("Ha ocurrido un error, por favor intente nuevamente.");
    }

    /**
     * Genera el reporte con la tabla de todos los cursos registrados en el sistema.
     *
     * @param cursoController Controlador de cursos.
     * @throws IOException Errores de entrada/salida de datos.
     */
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
